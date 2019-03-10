package com.qub.Technopoly.actor;

import com.qub.Technopoly.inventory.Inventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private static final String EXPECTED_NAME = "Lars";
    private Actor TEST_PLAYER = new Player(EXPECTED_NAME, new Inventory());

    @Test
    public void setActiveIsActive() {
        TEST_PLAYER.SetActive();
        assertTrue(TEST_PLAYER.IsActive());
    }

    @Test
    public void setInactiveIsInactive() {
        TEST_PLAYER.SetActive();
        TEST_PLAYER.SetInactive();
        assertFalse(TEST_PLAYER.IsActive());
    }

    @Test
    public void playerIsInactiveByDefault() {
        assertFalse(TEST_PLAYER.IsActive());
    }

    @Test
    public void getNameReturnsExpected() {
        assertEquals(EXPECTED_NAME, TEST_PLAYER.getActorName());
    }
}
