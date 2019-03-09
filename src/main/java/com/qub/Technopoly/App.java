package com.qub.Technopoly;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

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

        final var game = Game.getInstance();
        game.Start();

        while (game.isGameRunning()) {
          game.Update();
        }

        // Dice dice = new DeveloperDice(DiceConfig.getDefault());
        // dice.roll();
        //
        // dice = new RandomDice(new Random(), DiceConfig.getDefault());
        // dice.roll();
    }
}
