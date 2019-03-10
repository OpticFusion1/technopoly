package com.qub.Technopoly;

import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.exception.GameStateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game = new Game(new Board());

    @Test
    public void whenStartGameIsRunning() {
        game.start();
        assertTrue(game.isGameRunning());
    }

    @Test
    public void gameIsNotRunningByDefault() {
        assertFalse(game.isGameRunning());
    }

    @Test
    public void whenStopGameIsNotRunning() {
        game.start();
        game.stop();
        assertFalse(game.isGameRunning());
    }

    @Test
    public void startWhenRunningThrowsGameStateException() {
        game.start();

        assertThrows(GameStateException.class, () -> game.start());
    }

    @Test
    public void stopWhenNotRunningThrowsGameStateException() {
        assertThrows(GameStateException.class, () -> game.stop());
    }

    @Test
    public void updateWhenNotRunningThrowsGameStateException() {
        assertThrows(GameStateException.class, () -> game.update());
    }
}
