package com.publicissapient.exeption;

import lombok.Getter;

@Getter
public class CreditCardServiceException extends RuntimeException {

  private final ErrorType errorType;

  public CreditCardServiceException(ErrorType errorType) {
    this.errorType = errorType;
  }
}
