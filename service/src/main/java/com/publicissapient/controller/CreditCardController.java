package com.publicissapient.controller;

import static com.publicissapient.constant.CreditCardConstants.CREDIT_CARD_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import com.publicissapient.dto.request.CreditCardRequest;
import com.publicissapient.dto.response.CreditCardResponse;
import com.publicissapient.services.CreditCardService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CreditCardController {

  @Autowired
  public CreditCardService creditCardService;

  @ApiOperation(
      value = "Credit Card",
      nickname = "Credit Card",
      response = ResponseEntity.class)
  @PostMapping(
      value = CREDIT_CARD_URL,
      consumes = APPLICATION_JSON_UTF8_VALUE,
      produces = APPLICATION_JSON_UTF8_VALUE)
  public HttpEntity<?> addCreditCad(
      @RequestBody @Valid CreditCardRequest creditCardRequest) {
    creditCardService.addCard(creditCardRequest);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(
      value = "List credit card",
      nickname = "List credit card",
      response = ResponseEntity.class)
  @GetMapping(
      value = CREDIT_CARD_URL)
  public ResponseEntity<CreditCardResponse> getCardList() {

    CreditCardResponse creditCardResponses = creditCardService
        .getCardList();

    return ResponseEntity.ok().body(creditCardResponses);
  }
}
