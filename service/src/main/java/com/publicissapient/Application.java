package com.publicissapient;

import com.publicissapient.repository.CreditCardRepository;
import com.publicissapient.repository.entity.CreditCard;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


  @Bean
  public CommandLineRunner commandlineRunner(CreditCardRepository creditCardRepository)
      throws Exception {

    return args -> {
      creditCardRepository.deleteAllInBatch();
      List<CreditCard> creditCards = new ArrayList<>();
      creditCards.add(createCreditCard("Jon Dan", "4024007155489841", new BigDecimal("45.70")));
      creditCards.add(createCreditCard("Alex Nixon", "5205403133330694", new BigDecimal("135.12")));

      creditCardRepository.saveAll(creditCards);
    };
  }

  private CreditCard createCreditCard(String name, String cardNumber, BigDecimal limit) {
    LocalDateTime localDateTime = LocalDateTime.now();
    return CreditCard.builder()
        .fullName(name)
        .cardNumber(cardNumber)
        .limit(limit)
        .balance(new BigDecimal("0"))
        .createDateTime(localDateTime)
        .updateDateTime(localDateTime)
        .build();
  }
}
