package com.publicissapient.util;

import com.publicissapient.dto.CreditCardDto;
import com.publicissapient.dto.request.CreditCardRequest;
import com.publicissapient.dto.response.CreditCardResponse;
import com.publicissapient.repository.entity.CreditCard;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {

  public static final String VALID_CARD_1 = "4024007155489841";
  public static final String VALID_CARD_2 = "5205403133330694";
  public static final String IN_VALID_CARD = "4014007177633822";
  public static final String NAME_1 = "Jon Dan";
  public static final String NAME_2 = "Alex Nixon";
  public static final BigDecimal BALANCE_1 = new BigDecimal("0.00");
  public static final BigDecimal BALANCE_2 = new BigDecimal("0.00");
  public static final BigDecimal LIMIT_1 = new BigDecimal("45.70");
  public static final BigDecimal LIMIT_2 = new BigDecimal("13.10");
  public static final LocalDateTime NOW = LocalDateTime.now();

  public static CreditCardRequest buildCreditCardRequest() {
    return CreditCardRequest.builder()
        .cardNumber(VALID_CARD_1)
        .fullName(NAME_1)
        .limit(LIMIT_1)
        .build();
  }

  public static CreditCardRequest buildCreditCardRequestInvalid() {
    return CreditCardRequest.builder()
        .cardNumber(IN_VALID_CARD)
        .fullName(NAME_1)
        .limit(LIMIT_1)
        .build();
  }

  public static CreditCardDto buildCreditCardDto() {
    return CreditCardDto.builder()
        .cardNumber(VALID_CARD_1)
        .fullName(NAME_1)
        .balance(BALANCE_1)
        .limit(LIMIT_1)
        .build();
  }

  public static CreditCardResponse buildCreditCardResponse() {
    return CreditCardResponse.builder()
        .creditCardList(List.copyOf(buildCreditCardDtoList()))
        .build();
  }

  public static List<CreditCardDto> buildCreditCardDtoList() {
    List<CreditCardDto> creditCardDtos = new ArrayList<>();

    creditCardDtos.add(
        CreditCardDto.builder()
            .cardNumber(VALID_CARD_1)
            .fullName(NAME_1)
            .balance(BALANCE_1)
            .limit(LIMIT_1)
            .build());

    creditCardDtos.add(
        CreditCardDto.builder()
            .cardNumber(VALID_CARD_2)
            .fullName(NAME_2)
            .balance(BALANCE_2)
            .limit(LIMIT_2)
            .build());

    return creditCardDtos;
  }

  public static CreditCard buildCreditCard1() {
    return CreditCard.builder()
        .cardNumber(VALID_CARD_1)
        .fullName(NAME_1)
        .balance(BALANCE_1)
        .limit(LIMIT_1)
        .updateDateTime(NOW)
        .createDateTime(NOW)
        .build();
  }

  public static CreditCard buildCreditCard2() {
    return CreditCard.builder()
        .cardNumber(VALID_CARD_2)
        .fullName(NAME_2)
        .balance(BALANCE_2)
        .limit(LIMIT_2)
        .updateDateTime(NOW)
        .createDateTime(NOW)
        .build();
  }
}
