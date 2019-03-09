package com.qub.Technopoly.dice;

import com.qub.Technopoly.input.InputSource;
import com.qub.Technopoly.input.ScannerInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeveloperDiceTest {
  
  private final DiceConfig diceConfig = DiceConfig.getDEFAULT();
  private final InputSource mockInput = mock(InputSource.class);
  private Dice dice;

  @BeforeEach
  void setUp() throws Exception {
    dice = new DeveloperDice(diceConfig, mockInput);
  }
  

  @Test
  void rollWithValidValueReturnsSuppliedValue() {   
    int expected = 5;
    when(mockInput.getNextInt()).thenReturn(expected);
    assertEquals(expected, dice.roll());
  }
  
  @Test
  void rollWithDiceConfigMaxReturnsSuppliedValue() {
    int expected = diceConfig.getMaxRoll();
    when(mockInput.getNextInt()).thenReturn(expected);
    assertEquals(expected, dice.roll());
  }
  
  @Test
  void rollWithDiceConfigMinReturnsSuppliedValue() {
    int expected = diceConfig.getMinRoll();
    when(mockInput.getNextInt()).thenReturn(expected);
    assertEquals(expected, dice.roll());
  }
}
