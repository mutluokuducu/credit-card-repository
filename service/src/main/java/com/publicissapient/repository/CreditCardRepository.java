package com.publicissapient.repository;

import com.publicissapient.repository.entity.CreditCard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

  Optional<CreditCard> findByCardNumber(String cardNumber);

}
