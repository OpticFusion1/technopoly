package com.qub.Technopoly;

import com.qub.Technopoly.io.PrettySystemOutput;

public class App {
    public static void main(String[] args) {

        var outputSource = new PrettySystemOutput();
        outputSource.writeTitle("Welcome to Technopoly!");

        final var game = Game.getInstance();
        game.start();

        while (game.isGameRunning()) {
            game.update();
        }
    }
}
