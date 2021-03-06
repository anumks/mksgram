package me.jpomykala.javagram.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomUtilsTest {

  @Test
  public void always_return_false_on_0() {

    //given
    double probability = 0.0;

    //when
    boolean output = RandomUtils.getTrueWithProbability(probability);

    //then
    assertFalse(output);

  }

  @Test
  public void always_return_true_on_1() {

    //given
    double probability = 1.0;

    //when
    boolean output = RandomUtils.getTrueWithProbability(probability);

    //then
    assertTrue(output);
  }
}
