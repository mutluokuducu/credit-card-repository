package com.publicissapient.controller;

import static com.publicissapient.constant.CreditCardConstants.CREDIT_CARD_URL;
import static com.publicissapient.exeption.ErrorType.CREDIT_CARD_FORMAT_INVALID;
import static com.publicissapient.util.JsonUtils.objectToJson;
import static com.publicissapient.util.ObjectFactory.IN_VALID_CARD;
import static com.publicissapient.util.ObjectFactory.NAME_1;
import static com.publicissapient.util.ObjectFactory.NAME_2;
import static com.publicissapient.util.ObjectFactory.VALID_CARD_1;
import static com.publicissapient.util.ObjectFactory.VALID_CARD_2;
import static com.publicissapient.util.ObjectFactory.buildCreditCardRequest;
import static com.publicissapient.util.ObjectFactory.buildCreditCardRequestInvalid;
import static com.publicissapient.util.ObjectFactory.buildCreditCardResponse;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.publicissapient.dto.request.CreditCardRequest;
import com.publicissapient.exeption.CreditCardServiceException;
import com.publicissapient.exeption.GlobalExceptionHandler;
import com.publicissapient.services.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CreditCardControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CreditCardService creditCardService;

  @InjectMocks
  private CreditCardController creditCardController;

  @BeforeEach
  public void init() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(creditCardController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Test
  void createCreditCard_shouldReturnIsOK_whenCreditCardDetailsValid() throws Exception {
    CreditCardRequest creditCardRequest = buildCreditCardRequest();
    this.mockMvc
        .perform(
            post(CREDIT_CARD_URL)
                .content(objectToJson(creditCardRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk());
  }

  @Test
  void createCreditCard_shouldReturnIsBadRequest_whenCreditCardDetailsInValid() throws Exception {
    CreditCardRequest creditCardRequest = buildCreditCardRequestInvalid();
    creditCardRequest.setCardNumber(IN_VALID_CARD);
    doThrow(new CreditCardServiceException(CREDIT_CARD_FORMAT_INVALID))
        .when(creditCardService).addCard(creditCardRequest);

    this.mockMvc
        .perform(
            post(CREDIT_CARD_URL)
                .content(objectToJson(creditCardRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isBadRequest());
  }

  @Test
  void createCreditCard_shouldGetCardList() throws Exception {
    when(creditCardService
        .getCardList())
        .thenReturn(buildCreditCardResponse());

    this.mockMvc
        .perform(
            get(CREDIT_CARD_URL)
                .contentType(APPLICATION_JSON_UTF8_VALUE)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.creditCardList").isNotEmpty())
        .andExpect(jsonPath("$.creditCardList[0].fullName").value(NAME_1))
        .andExpect(jsonPath("$.creditCardList[0].cardNumber").value(VALID_CARD_1))

        .andExpect(jsonPath("$.creditCardList[1].fullName").value(NAME_2))
        .andExpect(jsonPath("$.creditCardList[1].cardNumber").value(VALID_CARD_2));
  }
}