package test.com.qub.technopolis.dice;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.com.qub.technopolis.dice.DeveloperDice;
import main.com.qub.technopolis.dice.Dice;
import main.com.qub.technopolis.dice.DiceConfig;

class DeveloperDiceTest {
  
  private final DiceConfig diceConfig = DiceConfig.getDefault();
  private Dice dice;

  @BeforeEach
  void setUp() throws Exception {
    dice = new DeveloperDice(diceConfig);
  }
  

  @Test
  void rollWithValidValueReturnsSuppliedValue() {   
    int expected = 5;    
    setInput(expected);
    assertEquals(expected, dice.roll());
  }
  
  @Test
  void rollWithDiceConfigMaxReturnsSuppliedValue() {
    int expected = diceConfig.getMaxRoll();
    setInput(expected);
    assertEquals(expected, dice.roll());
  }
  
  @Test
  void rollWithDiceConfigMinReturnsSuppliedValue() {
    int expected = diceConfig.getMinRoll();
    setInput(expected);
    assertEquals(expected, dice.roll());
  }
  
  private void setInput(int input) {
    var in = new ByteArrayInputStream(String.valueOf(input).getBytes());
    System.setIn(in);
  }
}
