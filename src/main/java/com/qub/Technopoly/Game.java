package com.qub.Technopoly;

import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.exception.GameStateException;

/**
 * Bootstrap responsible for the game loop.
 *
 * @author lakrs
 */
public class Game {
    // TODO - Initialize Game..
    // TODO - Allow loading game from disk...

    private static Game instance;
    private Board board;
    private boolean gameRunning = false;
    private Game() {
        // Prevent instantiation
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    /**
     * Ends the game
     * <p>
     * Throws {@link GameStateException} if this method is called when the game is not running
     *
     * @throws GameStateException
     */
    public void stop() throws GameStateException {
        if (!gameRunning) {
            throw new GameStateException("Can't stop game as game is not running");
        }

        // TODO - stop game.
        gameRunning = false;
    }

    /**
     * Check whether the game is running
     *
     * @return
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Starts a new game
     * <p>
     * Throws {@link GameStateException} if this method is called when the game is already running
     *
     * @throws GameStateException
     */
    public void start() throws GameStateException {
        if (gameRunning) {
            throw new GameStateException("Can't start game as game is already running");
        }

        // TODO - start game.
        // TODO - Create Players. Add to Board.
        board = new Board();

        board.addActor(new Player("Ronald Reagan"));
        board.addActor(new Player("Theresa Mays"));
        board.addActor(new Player("Donald Trump"));
        board.addActor(new Player("Vladimir Putin"));

        gameRunning = true;
    }

    /**
     * The game loop. Is responsible for updating all actors in the game, and calling methods on them.
     * <p>
     * Throws {@link GameStateException} if this method is called when the game is not running
     *
     * @throws GameStateException
     */
    public void update() throws GameStateException {
        if (!gameRunning) {
            throw new GameStateException("Can't update game as game is not running");
        }

        // TODO - update game.
        final var nextActor = board.getNextActor();
        nextActor.SetActive();

        while (nextActor.IsActive()) {
            if (nextActor.Update()) {
                nextActor.SetInactive();
            }
        }
    }
}
