package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.io.InputSource;
import com.qub.Technopoly.io.OutputSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.qub.Technopoly.io.IOHelper.getOutputSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StartNewGameActionTest {

    private static final int AMOUNT_PLAYERS = 2;
    private static final String PLAYER_NAME_ONE = "Player1";
    private static final String PLAYER_NAME_TWO = "Player2";

    private Game mockGame;
    private Board board;
    private InputSource mockInputSource;
    private OutputSource outputSource;
    private StartNewGameAction startNewGameAction;

    @BeforeEach
    public void setup() {
        mockGame = mock(Game.class);
        board = new Board();
        mockInputSource = mock(InputSource.class);
        outputSource = getOutputSource();

        startNewGameAction = new StartNewGameAction(mockGame, board, outputSource, mockInputSource);
    }

    @Test
    public void executeWithNegativeOnePlayersReturnsFalse() {
        when(mockInputSource.getNextInt()).thenReturn(-1);
        assertFalse(startNewGameAction.execute());
    }

    @Test
    public void executeWithGreaterThanMaxPlayersReturnsFalse() {
        when(mockInputSource.getNextInt())
            .thenReturn(Config.getConfig().getPlayerConfig().getMaxPlayers() + 1);
        assertFalse(startNewGameAction.execute());
    }

    @Test
    public void executeWithMinPlayersReturnsTrue() {
        when(mockInputSource.getNextInt())
            .thenReturn(Config.getConfig().getPlayerConfig().getMinPlayers());
        when(mockInputSource.getNextString()).thenReturn("Player One", "Player Two");
        assertTrue(startNewGameAction.execute());
    }

    @Test
    public void executeWithMaxPlayersReturnsTrue() {
        when(mockInputSource.getNextInt())
            .thenReturn(Config.getConfig().getPlayerConfig().getMaxPlayers());
        when(mockInputSource.getNextString())
            .thenReturn("Player One", "Player Two", "Player Three", "Player Four");
        assertTrue(startNewGameAction.execute());
    }

    @Test
    public void executeOnSuccessStartsGame() {
        when(mockInputSource.getNextInt())
            .thenReturn(Config.getConfig().getPlayerConfig().getMaxPlayers());
        when(mockInputSource.getNextString())
            .thenReturn("Player One", "Player Two", "Player Three", "Player Four");

        startNewGameAction.execute();

        verify(mockGame).start();
    }

    @Test
    public void executeOnSuccessHasExpectedPlayerNamesInBoard() {

        var expectedActorNames =
            new String[] {"Player One", "Player Two", "Player Three", "Player Four"};

        when(mockInputSource.getNextInt())
            .thenReturn(Config.getConfig().getPlayerConfig().getMaxPlayers());
        when(mockInputSource.getNextString())
            .thenReturn("Player One", "Player Two", "Player Three", "Player Four");

        startNewGameAction.execute();

        var actualActorNames =
            Arrays.stream(board.getActors()).map(Actor::getActorName).collect(Collectors.toList());

        assertThat(actualActorNames, containsInAnyOrder(expectedActorNames));
    }
}
