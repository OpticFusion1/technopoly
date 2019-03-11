package com.qub.Technopoly.tile;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.StartConfig;
import com.qub.Technopoly.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StartTest {
    private StartConfig testStartConfig;
    private Start testStart;
    private Actor testActor;
    private Board testBoard;

    @BeforeEach
    public void setup() {
        testStartConfig = Config.getConfig().getStartConfig();
        testStart = new Start(testStartConfig);
        testActor = new Player("TEST PLAYER", new Inventory());
        testBoard = new Board();
    }

    @Test
    public void getNameReturnsExpectedName() {
        assertEquals(testStartConfig.getName(), testStart.getName());
    }

    @Test
    public void getDescriptionStringContainingValueAndCurrencyName() {
        var startDescription = testStart.getDescription();
        assertThat(startDescription,
            containsString(String.valueOf(testStartConfig.getStartPassBonus())));
        assertThat(startDescription,
            containsString(Config.getConfig().getInventoryConfig().getCurrencyName()));
    }

    @Test
    public void onPassAddsStartBonusToActorInventory() {
        var currentActorBalance = testActor.getInventory().getCurrentBalance();
        testStart.onPass(testActor);

        var difference = testActor.getInventory().getCurrentBalance() - currentActorBalance;
        assertEquals(testStartConfig.getStartPassBonus(), difference);
    }

    @Test
    public void onLandReturnsNull() {
        assertNull(testStart.onLand(testActor, testBoard));
    }

    @Test
    public void toStringReturnsStringContainingStartName() {
        assertThat(testStart.toString(), containsString(testStart.getName()));
    }
}
