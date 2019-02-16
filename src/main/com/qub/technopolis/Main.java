package main.com.qub.technopolis;

import java.util.Random;
import main.com.qub.technopolis.dice.DeveloperDice;
import main.com.qub.technopolis.dice.Dice;
import main.com.qub.technopolis.dice.DiceConfig;
import main.com.qub.technopolis.dice.RandomDice;

public class Main {

  public static void main(String[] args) {

    // var game = Game.getInstance();
    // game.Start();
    //
    // while(game.isGameRunning()) {
    // game.Update();
    // }

    // TODO - This will not be called by this class. Probably.
    // game.End();

    // For now, you can put code in this method for testing. Later on, main will be running a game
    // loop.
    
    Dice dice = new DeveloperDice(DiceConfig.getDefault());
    dice.roll();

    dice = new RandomDice(new Random(), DiceConfig.getDefault());
    dice.roll();
  }
}
