package com.publicissapient.dto.response;

import com.publicissapient.dto.CreditCardDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardResponse {

  private List<CreditCardDto> creditCardList;

}