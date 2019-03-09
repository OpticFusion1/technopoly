package com.qub.Technopoly.dice;

import java.util.Scanner;

public class DeveloperDice implements Dice {
  
  private final DiceConfig diceConfig;
  
  public DeveloperDice(DiceConfig diceConfig) {
    this.diceConfig = diceConfig;
  }

  @Override
  public int roll() {
    
    var minRoll = diceConfig.getMinRoll();
    var maxRoll = diceConfig.getMaxRoll();

    try (var scanner = new Scanner(System.in)) {
      var hasRoll = false;
      int roll = 0;
      do {
        System.out.printf("Select a Dice roll (%s-%s):", minRoll, maxRoll);

        if (!scanner.hasNextInt()) {
          scanner.next();
          System.out.println("This is not a valid number!");
          continue;
        }


        roll = scanner.nextInt();
        if ((roll >= minRoll) && (roll <= maxRoll)) {
          hasRoll = true;
        } else {
          System.out.printf("Number must be between %s-%s\n", minRoll, maxRoll);
        }
      } while (!hasRoll);

      return roll;
    }
  }

}
