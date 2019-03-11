package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewTurnActionGroupTest {
    private Actor testActor;
    private Board testBoard;

    private ActionGroup actionGroup;

    @BeforeEach
    public void setup() {
        testActor = new Player("Test Player", new Inventory());
        testBoard = new Board();

        actionGroup = new NewTurnActionGroup(testActor, testBoard);
    }

    @Test
    public void describeDoesNotThrow() {
        actionGroup.describe();
    }

    @Test
    public void describeActionsDoesNotThrow() {
        actionGroup.describeActions();
    }

    @Test
    public void getActionsIsNotNull() {
        assertNotNull(actionGroup.getActions());
    }

    @Test
    public void getActionsIsNotEmpty() {
        assertTrue(actionGroup.getActions().length > 0);
    }
}
