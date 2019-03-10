package com.qub.Technopoly;

import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.PrettySystemOutput;

public class App {
    public static void main(String[] args) {

        final var outputSource = IOHelper.getOutputSource();
        outputSource.writeTitle("Welcome to Technopoly!");
        outputSource.writeBody("To get started, select one of the options below:");


//        final var game = Game.getInstance();
//        game.start();
//
//        while (game.isGameRunning()) {
//            game.update();
//        }
    }
}
