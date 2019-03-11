package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.inventory.Inventory;
import com.qub.Technopoly.tile.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnownedPropertyActionGroupTest {

    private Actor testActor;
    private Property testProperty;
    private Board testBoard;

    private ActionGroup actionGroup;

    @BeforeEach
    public void setup() {
        testActor = new Player("Test Player", new Inventory());
        testProperty =
            new Property(Config.getConfig().getFieldConfigs()[0].getPropertyConfigs()[0]);
        testBoard = new Board();

        actionGroup = new UnownedPropertyActionGroup(testActor, testProperty, testBoard);
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
