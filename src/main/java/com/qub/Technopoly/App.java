package com.qub.Technopoly;

import com.qub.Technopoly.actions.category.GameInitializeActionCategory;
import com.qub.Technopoly.board.Board;

public class App {
    public static void main(String[] args) {

        final var board = new Board();
        final var game = new Game(board);
        var init = new GameInitializeActionCategory(game, board);
        init.describe();

        // Halt execution while not initialized
        while(!init.execute());

        while (game.isGameRunning()) {
            game.update();
        }
    }
}
