package com.qub.Technopoly.dice;

import java.util.Random;

/**
 * The RandomDice class uses an instance of {@link Random} to roll a number between
 * {@value #MIN_ROLL} and {@value #MAX_ROLL}
 * @author lakrs
 *
 */
public class RandomDice implements Dice {
  
  private final Random random;
  private final DiceConfig diceConfig;
  
  public RandomDice(Random random, DiceConfig diceConfig) {
    this.random = random;
    this.diceConfig = diceConfig;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int roll() {
    return random.nextInt(diceConfig.getMaxRoll()) + diceConfig.getMinRoll();
  }

}
