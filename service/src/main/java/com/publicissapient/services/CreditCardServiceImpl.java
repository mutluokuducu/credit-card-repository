package com.publicissapient.services;

import static com.publicissapient.exeption.ErrorType.CREDIT_CARD_ALREADY_CREATED;
import static com.publicissapient.exeption.ErrorType.CREDIT_CARD_FORMAT_INVALID;
import static com.publicissapient.util.LuhnUtils.luhnCheck;
import static java.time.LocalDateTime.now;

import com.publicissapient.dto.CreditCardDto;
import com.publicissapient.dto.request.CreditCardRequest;
import com.publicissapient.dto.response.CreditCardResponse;
import com.publicissapient.exeption.CreditCardServiceException;
import com.publicissapient.mapper.CreditCardMapper;
import com.publicissapient.repository.CreditCardRepository;
import com.publicissapient.repository.entity.CreditCard;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {

  @Autowired
  private CreditCardRepository creditCardRepository;

  @Autowired
  private CreditCardMapper creditCardMapper;

  @Override
  public void addCard(CreditCardRequest creditCardRequest) {

    creditCardRepository.findByCardNumber(creditCardRequest.getCardNumber())
        .ifPresentOrElse(cardActivity -> {
          log.error("This card was created. Card number: {}", cardActivity.getCardNumber());
          throw new CreditCardServiceException(CREDIT_CARD_ALREADY_CREATED);
        }, () -> {
          validate(creditCardRequest.getCardNumber());
          createCard(creditCardRequest);
        });
  }


  @Override
  public CreditCardResponse getCardList() {
    log.info("Get all credit card list");
    List<CreditCard> responseList = creditCardRepository.findAll();

    List<CreditCardDto> creditCardDtos = new ArrayList<>();
    for (CreditCard creditCard : responseList) {
      creditCardDtos.add(creditCardMapper.toCreditCardResponse(creditCard));
    }
    return CreditCardResponse.builder()
        .creditCardList(creditCardDtos)
        .build();
  }

  private void validate(final String cNumber) {
    if (!luhnCheck(cNumber)) {
      log.error("Invalid card. Card Number: {}", cNumber);
      throw new CreditCardServiceException(CREDIT_CARD_FORMAT_INVALID);
    }
  }

  private void createCard(CreditCardRequest creditCardRequest) {

    LocalDateTime now = now();
    CreditCard creditCard = CreditCard.builder()
        .fullName(creditCardRequest.getFullName())
        .cardNumber(creditCardRequest.getCardNumber())
        .balance(new BigDecimal("0.00"))
        .limit(creditCardRequest.getLimit())
        .createDateTime(now)
        .updateDateTime(now)
        .build();

    creditCardRepository.save(creditCard);
  }
}
