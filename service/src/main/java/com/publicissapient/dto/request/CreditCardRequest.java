package com.publicissapient.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {

  @NotBlank
  @Pattern(regexp = "^$|[A-Za-z0-9-/.&\\- ]{1,100}", message = "Invalid account name")
  private String fullName;

  @NotBlank
  @Pattern(regexp = "^[1-9]\\d*$", message = "Invalid card")
  @Size(max = 19, min = 13, message = "Invalid card id")
  private String cardNumber;

  @NotNull
  @DecimalMin(value = "01.00", message = "Maximum Credit limit must be greater than 1.00")
  @DecimalMax(value = "999999999.99", message = "Maximum Credit limit must be less than 99,999,999.99")
  @Digits(integer = 9, fraction = 2)
  private BigDecimal limit;

}