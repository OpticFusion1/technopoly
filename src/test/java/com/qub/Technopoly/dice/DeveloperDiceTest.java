package com.qub.Technopoly.dice;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

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
