package com.publicissapient.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "credit_card")
public class CreditCard {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "card_number", nullable = false)
  private String cardNumber;

  @Column(name = "balance_card", nullable = false)
  private BigDecimal balance;

  @Column(name = "limit_card", nullable = false)
  private BigDecimal limit;

  @Column(name = "create_date_time", updatable = false, nullable = false)
  private LocalDateTime createDateTime;

  @Column(name = "update_date_time")
  private LocalDateTime updateDateTime;
}