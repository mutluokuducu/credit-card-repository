package com.publicissapient.services;

import com.publicissapient.dto.request.CreditCardRequest;
import com.publicissapient.dto.response.CreditCardResponse;

public interface CreditCardService {

  void addCard(CreditCardRequest creditCardRequest);

  CreditCardResponse getCardList();
}
