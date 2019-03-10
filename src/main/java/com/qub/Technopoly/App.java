package com.qub.Technopoly;

public class App {
    public static void main(String[] args) {

        final var game = Game.getInstance();
        game.start();

        while (game.isGameRunning()) {
            game.update();
        }
    }
}
