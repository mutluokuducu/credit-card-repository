package com.publicissapient.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.publicissapient.dto.CreditCardDto;
import com.publicissapient.repository.entity.CreditCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface CreditCardMapper {

  CreditCardDto toCreditCardResponse(final CreditCard creditCard);
}

