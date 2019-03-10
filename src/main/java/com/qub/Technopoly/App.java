package com.qub.Technopoly;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        // var game = Game.getInstance();
        // game.start();
        //
        // while(game.isGameRunning()) {
        // game.update();
        // }

        // TODO - This will not be called by this class. Probably.
        // game.stop();

        // For now, you can put code in this method for testing. Later on, main will be running a game
        // loop.

        final var game = Game.getInstance();
        game.start();

        while (game.isGameRunning()) {
            game.update();
        }

        // Dice dice = new DeveloperDice(DiceConfig.getDefault());
        // dice.roll();
        //
        // dice = new RandomDice(new Random(), DiceConfig.getDefault());
        // dice.roll();
    }
}
