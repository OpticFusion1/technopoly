package test.com.qub.technopolis.dice;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import main.com.qub.technopolis.dice.Dice;
import main.com.qub.technopolis.dice.DiceConfig;
import main.com.qub.technopolis.dice.RandomDice;

class RandomDiceTest {
  
  private static final long RANDOM_SEED = 1234567;
  private final DiceConfig diceConfig = DiceConfig.getDefault();
  private Random seededRandom;
  private Dice dice;
  

  @BeforeEach
  void setUp() throws Exception {
    seededRandom = new Random(RANDOM_SEED);
    dice = new RandomDice(seededRandom, diceConfig);
  }

  @RepeatedTest(100)
  void rollReturnsValuesGreaterThanOrEqualToMinRoll() {
    var roll = dice.roll();
    assertTrue(roll >= diceConfig.getMinRoll());
  }
  
  @RepeatedTest(100)
  void rollReturnsValuesGreaterThanOrEqualToMaxRoll() {
    var roll = dice.roll();
    assertTrue(diceConfig.getMaxRoll() >= roll);
  }
}