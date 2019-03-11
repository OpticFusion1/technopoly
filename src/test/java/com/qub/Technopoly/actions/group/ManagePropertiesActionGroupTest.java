package com.qub.Technopoly.actions.group;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagePropertiesActionGroupTest {
    private Actor testActor;
    private ActionGroup actionGroup;

    @BeforeEach
    public void setup() {
        testActor = new Player("Test Player", new Inventory());
        actionGroup = new ManagePropertiesActionGroup(testActor);
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
