package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.inventory.Inventory;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.qub.Technopoly.io.IOHelper.getOutputSource;
import static org.junit.jupiter.api.Assertions.*;

public class UpgradePropertyActionTest {
    private Actor testActor;
    private Property testProperty;
    private OutputSource outputSource;
    private UpgradePropertyAction upgradePropertyAction;

    @BeforeEach
    public void setup() {
        testActor = new Player("Test Player", new Inventory());
        testProperty =
            new Property(Config.getConfig().getFieldConfigs()[0].getPropertyConfigs()[0]);
        outputSource = getOutputSource();
        upgradePropertyAction =
            new UpgradePropertyAction(testActor, testProperty, outputSource);
    }

    @Test
    public void executeReturnsFalseWhenNotCanUpgradeProperty() {
        while (testProperty.canUpgrade()) {
            testProperty.addHouse();
        }

        assertFalse(upgradePropertyAction.execute());
    }

    @Test
    public void executeReturnsFalseWhenActorCanNotAffordUpgrade() {
        testActor.getInventory().remove(testActor.getInventory().getCurrentBalance());
        assertFalse(upgradePropertyAction.execute());
    }

    @Test
    public void executeReturnsTrueWhenActorCanAffordUpgrade() {
        assertTrue(upgradePropertyAction.execute());
    }

    @Test
    public void executeDeductsPriceFromPlayerInventoryWhenUpgraded() {
        var expectedBalance =
            testActor.getInventory().getCurrentBalance() - testProperty.getNextHouseInfo()
                .getHousePrice();
        upgradePropertyAction.execute();

        assertEquals(expectedBalance, testActor.getInventory().getCurrentBalance());
    }

    @Test
    public void executeAddsHouseToPropertyWhenUpgraded() {
        var expectedHouseCount = testProperty.getCurrentHouses() + 1;
        upgradePropertyAction.execute();

        assertEquals(expectedHouseCount, testProperty.getCurrentHouses());
    }
}
