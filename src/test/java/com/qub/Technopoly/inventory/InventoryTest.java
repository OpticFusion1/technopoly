package com.qub.Technopoly.inventory;

import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.exception.InventoryException;
import com.qub.Technopoly.tile.Property;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory testInventory;
    private Property testOwnable1;
    private Property testOwnable2;

    @BeforeEach
    public void setup() {
        testInventory = new Inventory();
        testOwnable1 =
            new Property(Config.getConfig().getFieldConfigs()[0].getPropertyConfigs()[0]);
        testOwnable2 =
            new Property(Config.getConfig().getFieldConfigs()[0].getPropertyConfigs()[1]);
    }

    @Test
    public void addDoesNotThrow() {
        testInventory.add(testOwnable1);
        testInventory.add(testOwnable2);
    }

    @Test
    public void addWithDuplicateThrowsInventoryException() {
        testInventory.add(testOwnable1);
        assertThrows(InventoryException.class, () -> testInventory.add(testOwnable1));
    }

    @Test
    public void getCurrentBalanceIsStartBalanceByDefault() {
        assertEquals(Config.getConfig().getInventoryConfig().getStartBalance(),
            testInventory.getCurrentBalance());
    }

    @Test
    public void addCurrencyUpdatesCurrencyAsExpected() {
        var startBalance = testInventory.getCurrentBalance();
        var toAdd = 200;

        testInventory.add(toAdd);

        assertEquals(startBalance + toAdd, testInventory.getCurrentBalance());
    }

    @Test
    public void removeOwnableDoesNotThrow() {
        testInventory.add(testOwnable1);
        testInventory.remove(testOwnable1);
    }

    @Test
    public void removeOwnableWithoutOwnableInInventoryThrowsInventoryException() {
        assertThrows(InventoryException.class, () -> testInventory.remove(testOwnable1));
    }

    @Test
    public void containsReturnsTrueWhenOwnableIsInInventory() {
        testInventory.add(testOwnable1);
        assertTrue(testInventory.contains(testOwnable1));
    }

    @Test
    public void containsReturnsFalseWhenOwnableNotInInventory() {
        assertFalse(testInventory.contains(testOwnable1));
    }

    @Test
    public void getCountInInventoryForTypeReturnsExpectedCount() {
        testInventory.add(testOwnable1);
        testInventory.add(testOwnable2);

        assertEquals(2, testInventory.getCountInInventory(testOwnable1.getClass()));
    }

    @Test
    public void getCountInInventoryForTypeReturnsZeroWhenZeroItems() {

        assertEquals(0, testInventory.getCountInInventory(testOwnable1.getClass()));
    }

    @Test
    public void getTypeInInventoryReturnsExpectedProperties() {
        testInventory.add(testOwnable1);
        testInventory.add(testOwnable2);

        var expected = ArrayUtils.toArray(testOwnable1, testOwnable2);
        var actual = testInventory.getTypeInInventory(testOwnable1.getClass());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getTypeInInventoryReturnsEmptyArrayWhenZeroItems() {
        assertEquals(0, testInventory.getTypeInInventory(testOwnable1.getClass()).length);
    }

    @Test
    public void removeCurrencyUpdatesCurrencyAsExpected() {
        var startBalance = testInventory.getCurrentBalance();
        var toRemove = 200;

        testInventory.remove(toRemove);

        assertEquals(startBalance - toRemove, testInventory.getCurrentBalance());
    }
}
