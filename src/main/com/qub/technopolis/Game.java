package main.com.qub.technopolis;

import main.com.qub.technopolis.actor.Player;
import main.com.qub.technopolis.board.Board;
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

  private Board board;
  private boolean gameRunning = false;

  private Game() {
    // Prevent instantiation
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
  public void Start() throws GameStateException {
    if (gameRunning) {
      throw new GameStateException("Can't start game as game is already running");
    }

    // TODO - Start game.
    // TODO - Create Players. Add to Board.
    board = new Board();

    board.AddActor(new Player("Ronald Reagan"));
    board.AddActor(new Player("Theresa Mays"));
    board.AddActor(new Player("Donald Trump"));
    board.AddActor(new Player("Vladimir Putin"));

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
    final var nextActor = board.getNextActor();
    nextActor.SetActive();

    while (nextActor.IsActive()) {
      if (nextActor.Update()) {
        nextActor.SetInactive();
      }
    }
  }
}
