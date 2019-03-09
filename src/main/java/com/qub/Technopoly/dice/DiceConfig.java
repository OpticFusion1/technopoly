package com.qub.Technopoly.dice;

public class DiceConfig {

  private static final int MIN_ROLL = 1;
  private static final int MAX_ROLL = 6;
  private static final DiceConfig DEFAULT = new DiceConfig(MIN_ROLL, MAX_ROLL);

  /**
   * Get the default {@link DiceConfig}
   * 
   * @return
   */
  public static DiceConfig getDefault() {
    return DEFAULT;
  }

  private int minRoll;

  private int maxRoll;

  public DiceConfig(int minRoll, int maxRoll) {
    this.minRoll = minRoll;
    this.maxRoll = maxRoll;
  }

  /**
   * Get the maximum number that can be rolled by an instance of {@link Dice}
   * 
   * @return
   */
  public int getMaxRoll() {
    return maxRoll;
  }

  /**
   * Get the minimum number that can be rolled by an instance of {@link Dice}
   * 
   * @return
   */
  public int getMinRoll() {
    return minRoll;
  }
}
