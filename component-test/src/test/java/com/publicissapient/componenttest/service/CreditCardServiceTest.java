package com.publicissapient.componenttest.service;

import static com.publicissapient.constant.CreditCardConstants.CREDIT_CARD_URL;
import static com.publicissapient.util.ObjectFactory.VALID_CARD_1;
import static com.publicissapient.util.ObjectFactory.buildCreditCard1;
import static com.publicissapient.util.ObjectFactory.buildCreditCard2;
import static com.publicissapient.util.ObjectFactory.buildCreditCardRequest;
import static com.publicissapient.util.ObjectFactory.buildCreditCardRequestInvalid;
import static com.publicissapient.util.ObjectFactory.buildCreditCardResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import com.publicissapient.componenttest.BaseComponentTest;
import com.publicissapient.dto.request.CreditCardRequest;
import com.publicissapient.dto.response.CreditCardResponse;
import com.publicissapient.repository.CreditCardRepository;
import com.publicissapient.repository.entity.CreditCard;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class CreditCardServiceTest extends BaseComponentTest {

  @Autowired
  private CreditCardRepository creditCardRepository;

  @After
  public void clearDB() {
    creditCardRepository.deleteAll();
  }

  @Test
  public void shouldCreateCard() {
    CreditCardRequest creditCardRequest = buildCreditCardRequest();
    ResponseEntity<?> response = restTemplate.postForEntity(
        serverUrl.apply(CREDIT_CARD_URL),
        creditCardRequest,
        Void.class);

    assertThat(response.getStatusCode()).isEqualTo(OK);
    assertThat(creditCardRepository.findByCardNumber(VALID_CARD_1))
        .isPresent();
  }

  @Test
  public void shouldReturnCreditCard_GetBadRequest() {
    CreditCardRequest creditCardRequest = buildCreditCardRequestInvalid();

    ResponseEntity<?> response = restTemplate.postForEntity(
        serverUrl.apply(CREDIT_CARD_URL),
        creditCardRequest,
        Void.class);

    assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
  }

  @Test
  public void shouldGetCreditCardList() {
    insertCreditCard();
    ResponseEntity<CreditCardResponse> response = restTemplate
        .exchange(serverUrl.apply(CREDIT_CARD_URL),
            HttpMethod.GET,
            buildHeader(),
            CreditCardResponse.class);

    assertThat(response.getStatusCode()).isEqualTo(OK);
    assertThat(response.getBody()).isEqualTo(buildCreditCardResponse());

  }

  private void insertCreditCard() {

    List<CreditCard> creditCardList = new ArrayList<>();
    creditCardList.add(buildCreditCard1());
    creditCardList.add(buildCreditCard2());
    creditCardRepository.saveAll(creditCardList);
  }

  private HttpEntity<CreditCardResponse> buildHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new HttpEntity<>(headers);
  }
}