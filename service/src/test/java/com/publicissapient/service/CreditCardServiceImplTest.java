package com.publicissapient.service;

import static com.publicissapient.exeption.ErrorType.CREDIT_CARD_ALREADY_CREATED;
import static com.publicissapient.exeption.ErrorType.CREDIT_CARD_FORMAT_INVALID;
import static com.publicissapient.util.ObjectFactory.IN_VALID_CARD;
import static com.publicissapient.util.ObjectFactory.LIMIT_1;
import static com.publicissapient.util.ObjectFactory.NAME_1;
import static com.publicissapient.util.ObjectFactory.VALID_CARD_1;
import static com.publicissapient.util.ObjectFactory.buildCreditCard1;
import static com.publicissapient.util.ObjectFactory.buildCreditCardDto;
import static com.publicissapient.util.ObjectFactory.buildCreditCardRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.publicissapient.dto.CreditCardDto;
import com.publicissapient.dto.request.CreditCardRequest;
import com.publicissapient.dto.response.CreditCardResponse;
import com.publicissapient.exeption.CreditCardServiceException;
import com.publicissapient.mapper.CreditCardMapper;
import com.publicissapient.repository.CreditCardRepository;
import com.publicissapient.repository.entity.CreditCard;
import com.publicissapient.services.CreditCardServiceImpl;
import java.util.Optional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {

  @Mock
  private CreditCardRepository creditCardRepository;

  @Mock
  private CreditCardMapper creditCardMapper;

  @InjectMocks
  private CreditCardServiceImpl creditCardService;

  @Test
  void processCreditCard_shouldCreateCreditCard() {
    CreditCardRequest creditCardRequest = buildCreditCardRequest();
    when(creditCardRepository.findByCardNumber(VALID_CARD_1)).thenReturn(Optional.empty());

    assertThatCode(
        () ->
            creditCardService.addCard(creditCardRequest))
        .doesNotThrowAnyException();

    verify(creditCardRepository).save(argThat(
        (CreditCard creditCard1) -> {
          assertThat(creditCard1).isNotNull();
          assertThat(creditCard1.getCardNumber()).isEqualTo(VALID_CARD_1);
          assertThat(creditCard1.getFullName()).isEqualTo(NAME_1);
          return true;
        }));
  }

  @Test
  void processCreditCard_ShouldThrowException_WhenCardAlreadyCreated() {
    CreditCardRequest creditCardRequest = buildCreditCardRequest();
    CreditCard creditCard = buildCreditCard1();

    when(creditCardRepository.findByCardNumber(VALID_CARD_1)).thenReturn(Optional.of(creditCard));

    CreditCardServiceException exceptionThrown =
        assertThrows(
            CreditCardServiceException.class,
            () ->
                creditCardService.addCard(creditCardRequest));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(CREDIT_CARD_ALREADY_CREATED);
  }

  @Test
  void processCreditCard_ShouldThrowException_WhenInValidCard() {
    CreditCardRequest creditCardRequest = buildCreditCardRequest();
    creditCardRequest.setCardNumber(IN_VALID_CARD);

    when(creditCardRepository.findByCardNumber(IN_VALID_CARD)).thenReturn(Optional.empty());

    CreditCardServiceException exceptionThrown =
        assertThrows(
            CreditCardServiceException.class,
            () ->
                creditCardService.addCard(creditCardRequest));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(CREDIT_CARD_FORMAT_INVALID);
  }

  @Test
  void processCreditCard_ShouldGetCreditCardList() {
    CreditCard creditCard = buildCreditCard1();
    CreditCardDto creditCardDto = buildCreditCardDto();

    when(creditCardMapper.toCreditCardResponse(creditCard)).thenReturn(creditCardDto);

    when(creditCardRepository.findAll())
        .thenReturn(Lists.newArrayList(creditCard));

    CreditCardResponse cardList = creditCardService.getCardList();

    assertThat(cardList).isNotNull();
    assertThat(cardList.getCreditCardList().get(0).getFullName())
        .isEqualTo(NAME_1);
    assertThat(cardList.getCreditCardList().get(0).getCardNumber())
        .isEqualTo(VALID_CARD_1);
    assertThat(cardList.getCreditCardList().get(0).getLimit())
        .isEqualTo(LIMIT_1);
  }
}