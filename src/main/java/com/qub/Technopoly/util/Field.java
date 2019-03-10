package com.qub.Technopoly.util;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.FieldConfig;
import com.qub.Technopoly.config.PropertyConfig;
import com.qub.Technopoly.tile.Property;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Field {

    private static FieldConfig[] fieldConfigs = Config.getConfig().getFieldConfigs();

    private static Map<PropertyConfig, FieldConfig> fieldsForProperties;

    static {
        fieldsForProperties = new HashMap<>();
        Arrays.stream(fieldConfigs)
            .forEach(fc -> Arrays.stream(fc.getPropertyConfigs()).forEach(pc -> {
                fieldsForProperties.put(pc, fc);
            }));
    }

    private Field() {
        // Prevent instantiation
    }

    public static FieldConfig getFieldForProperty(Property property) {
        return fieldsForProperties.get(property.getPropertyConfig());
    }

    public static boolean hasAllPropertiesInFieldForProperty(Actor actor, Property property) {
        var field = fieldsForProperties.get(property.getPropertyConfig());
        var matchingProperties =
            Arrays.stream(actor.getInventory().getTypeInInventory(Property.class))
                .map(Property::getPropertyConfig)
                .filter(pc -> ArrayUtils.contains(field.getPropertyConfigs(), pc));

        return Arrays.equals(field.getPropertyConfigs(), matchingProperties.toArray());
    }
}
