package com.publicissapient.exeption;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public enum ErrorType {
  INTERNAL_ERROR(100, "An internal server error occurred", INTERNAL_SERVER_ERROR),
  CREDIT_CARD_FORMAT_INVALID(1001, "Credit card format invalid", BAD_REQUEST),
  CREDIT_CARD_ALREADY_CREATED(1002, "Credit card already created", CONFLICT),
  INVALID_PARAMETER_ERROR(1003, "Invalid field(s). ", UNPROCESSABLE_ENTITY);

  private int id;
  private String description;
  private HttpStatus httpStatus;

  ErrorType(int id, String description, HttpStatus httpStatus) {

    this.id = id;
    this.description = description;
    this.httpStatus = httpStatus;
  }
}
