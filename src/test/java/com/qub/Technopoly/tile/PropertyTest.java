package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.group.OwnedPropertyActionGroup;
import com.qub.Technopoly.actions.group.UnownedPropertyActionGroup;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.PropertyConfig;
import com.qub.Technopoly.exception.GameStateException;
import com.qub.Technopoly.inventory.Inventory;
import com.qub.Technopoly.util.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    private PropertyConfig testPropertyConfig;
    private Property testProperty;
    private Actor testActor;
    private Board testBoard;

    @BeforeEach
    public void setup() {
        testPropertyConfig = Config.getConfig().getFieldConfigs()[0].getPropertyConfigs()[0];
        testProperty = new Property(testPropertyConfig);
        testActor = new Player("TEST PLAYER", new Inventory());
        testBoard = new Board();
    }

    @Test
    public void getNameReturnsExpectedName() {
        assertEquals(testPropertyConfig.getPropertyName(), testProperty.getName());
    }

    @Test
    public void getDescriptionReturnsExpectedDescription() {
        assertEquals(testPropertyConfig.getPropertyDescription(), testProperty.getDescription());
    }

    @Test
    public void onPassDoesNotThrow() {
        testProperty.onPass(testActor);
    }

    @Test
    public void onLandReturnsUnownedPropertyActionGroupIfNotOwned() {
        var actionGroup = testProperty.onLand(testActor, testBoard);
        assertThat(actionGroup, instanceOf(UnownedPropertyActionGroup.class));
    }

    @Test
    public void onLandReturnsOwnedPropertyActionGroupIfOwned() {
        testProperty.setOwner(testActor);
        var actionGroup = testProperty.onLand(testActor, testBoard);
        assertThat(actionGroup, instanceOf(OwnedPropertyActionGroup.class));
    }

    @Test
    public void getRentWithNoHousesReturnsDefaultPropertyRent() {
        assertEquals(testPropertyConfig.getPropertyRent(), testProperty.getRent());
    }

    @Test
    public void getRentWithOneHouseReturnsExpectedHouseRent() {
        testProperty.addHouse();
        assertEquals(testPropertyConfig.getHouseConfigs()[0].getHouseRent(),
            testProperty.getRent());
    }

    @Test
    public void getRentWithTwoHousesReturnsExpectedHouseRent() {
        testProperty.addHouse();
        testProperty.addHouse();
        assertEquals(testPropertyConfig.getHouseConfigs()[1].getHouseRent(),
            testProperty.getRent());
    }

    @Test
    public void getPriceReturnsExpectedPrice() {
        assertEquals(testPropertyConfig.getPropertyPrice(), testProperty.getPrice());
    }

    @Test
    public void getNextHouseInfoReturnsExpectedHouseInfo() {
        var expectedHouse = testPropertyConfig.getHouseConfigs()[0];
        var actualHouse = testProperty.getNextHouseInfo();

        assertEquals(expectedHouse, actualHouse);
    }

    @Test
    public void getNextHouseInfoWithAddedHouseReturnsExpectedHouseInfo() {
        var expectedHouse = testPropertyConfig.getHouseConfigs()[1];
        testProperty.addHouse();
        var actualHouse = testProperty.getNextHouseInfo();
        assertEquals(expectedHouse, actualHouse);
    }

    @Test
    public void getNextHouseInfoWhenNotCanUpgradeThrowsGameStateException() {
        while (testProperty.canUpgrade()) {
            testProperty.addHouse();
        }

        assertThrows(GameStateException.class, () -> testProperty.getNextHouseInfo());
    }

    @Test
    public void addHouseDoesNotThrowWhileCanUpgrade() {
        while (testProperty.canUpgrade()) {
            testProperty.addHouse();
        }
    }

    @Test
    public void addHouseThrowsWhenCanNotUpgrade() {
        while (testProperty.canUpgrade()) {
            testProperty.addHouse();
        }
        assertThrows(GameStateException.class, () -> testProperty.addHouse());
    }

    @Test
    public void removeHouseDoesNotThrowWhileCurrentHousesIsGreaterThanZero() {
        while (testProperty.canUpgrade()) {
            testProperty.addHouse();
        }

        while (testProperty.getCurrentHouses() > 0) {
            testProperty.removeHouse();
        }
    }

    @Test
    public void removeHouseThrowsWhenCurrentHousesEqualsZero() {
        assertThrows(GameStateException.class, () -> testProperty.removeHouse());
    }

    @Test
    public void canUpgradeIsFalseWhenCurrentHousesEqualsHouseConfigsLength() {
        for (var i = 0; i < testPropertyConfig.getHouseConfigs().length; i++) {
            testProperty.addHouse();
        }
        assertFalse(testProperty.canUpgrade());
    }

    @Test
    public void toStringReturnsStringContainingFieldAndPropertyName() {
        var description = testProperty.toString();
        assertThat(description, containsString(Field.getFieldForProperty(testProperty).getName()));
        assertThat(description, containsString(testProperty.getName()));
    }
}
