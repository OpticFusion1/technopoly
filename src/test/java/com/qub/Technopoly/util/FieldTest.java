package com.qub.Technopoly.util;

import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.FieldConfig;
import com.qub.Technopoly.inventory.Inventory;
import com.qub.Technopoly.tile.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    private FieldConfig expectedFieldConfig;
    private Property testProperty;
    private Player testActorWithoutEnoughProperties;
    private Player testActorWithAllProperties;

    @BeforeEach
    public void setup(){
        expectedFieldConfig = Config.getConfig().getFieldConfigs()[0];
        testProperty = new Property(expectedFieldConfig.getPropertyConfigs()[0]);
        testActorWithoutEnoughProperties = new Player("TEST PLAYER", new Inventory());
        testActorWithoutEnoughProperties.getInventory().add(testProperty);

        testActorWithAllProperties = new Player("TEST PLAYER 2", new Inventory());
        Arrays.stream(expectedFieldConfig.getPropertyConfigs()).forEach(pc -> {
            var property = new Property(pc);
            testActorWithAllProperties.getInventory().add(property);
        });
    }

    @Test
    public void getFieldForPropertyReturnsExpectedField(){
        var actualField = Field.getFieldForProperty(testProperty);
        assertEquals(expectedFieldConfig, actualField);
    }

    @Test
    public void hasAllPropertiesInFieldForPropertyWithInsufficientPropertiesReturnsFalse(){
        assertFalse(Field.hasAllPropertiesInFieldForProperty(testActorWithoutEnoughProperties, testProperty));
    }

    @Test
    public void hasAllPropertiesInFieldForPropertyWithAllPropertiesReturnsTrue(){
        assertTrue(Field.hasAllPropertiesInFieldForProperty(testActorWithAllProperties, testProperty));
    }
}
