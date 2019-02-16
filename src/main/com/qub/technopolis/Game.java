package main.com.qub.technopolis;

import main.com.qub.technopolis.exception.GameStateException;

/**
 * Bootstrap responsible for the game loop.
 * 
 * @author lakrs
 *
 */
public class Game {
  // TODO - Initialize Game..
  // TODO - Allow loading game from disk...

  private static Game instance;

  public static Game getInstance() {
    if (instance == null) {
      instance = new Game();
    }

    return instance;
  }

  private boolean gameRunning = false;

  private Game() {
    // Prevent instantiation
  }
  
  /**
   * Check whether the game is running
   * @return
   */
  public boolean isGameRunning() {
    return gameRunning;
  }

  /**
   * Ends the game
   * <p>
   * Throws {@link GameStateException} if this method is called when the game is not running
   * 
   * @throws GameStateException
   */
  public void End() throws GameStateException {
    if (!gameRunning) {
      throw new GameStateException("Can't end game as game is not running");
    }

    // TODO - End game.
    gameRunning = false;
  }

  /**
   * Starts a new game
   * <p>
   * Throws {@link GameStateException} if this method is called when the game is already running
   * 
   * @throws GameStateException
   */
  public void Start() throws GameStateException {
    if (gameRunning) {
      throw new GameStateException("Can't start game as game is already running");
    }

    // TODO - Start game.
    gameRunning = true;
  }

  /**
   * The game loop. Is responsible for updating all actors in the game, and calling methods on them.
   * <p>
   * Throws {@link GameStateException} if this method is called when the game is not running
   * 
   * @throws GameStateException
   */
  public void Update() throws GameStateException {
    if (!gameRunning) {
      throw new GameStateException("Can't update game as game is not running");
    }

    // TODO - Update game.
  }
}
