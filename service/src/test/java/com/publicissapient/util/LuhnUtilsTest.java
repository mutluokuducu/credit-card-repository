package com.publicissapient.util;

import static com.publicissapient.util.LuhnUtils.luhnCheck;
import static com.publicissapient.util.ObjectFactory.IN_VALID_CARD;
import static com.publicissapient.util.ObjectFactory.VALID_CARD_1;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LuhnUtilsTest {

  @Test
  public void shouldCreditCardLuhnAlgorithmCheck_ReturnValid() {
    boolean isValid = luhnCheck(VALID_CARD_1);
    assertThat(isValid).isTrue();
  }

  @Test
  public void shouldCreditCardLuhnAlgorithmCheck_ReturnInValid() {
    boolean isValid = luhnCheck(IN_VALID_CARD);
    assertThat(isValid).isFalse();
  }

}
