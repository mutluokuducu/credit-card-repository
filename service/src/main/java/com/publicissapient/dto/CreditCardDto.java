package com.publicissapient.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {

  private String fullName;
  private String cardNumber;
  private BigDecimal balance;
  private BigDecimal limit;

}
