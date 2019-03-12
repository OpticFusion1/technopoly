package com.qub.Technopoly.util;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.FieldConfig;
import com.qub.Technopoly.config.PropertyConfig;
import com.qub.Technopoly.tile.Property;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * Gets the {@link FieldConfig} for a specific {@link Property}
     *
     * @param property The property to get the field for
     * @return
     */
    public static FieldConfig getFieldForProperty(Property property) {
        return fieldsForProperties.get(property.getPropertyConfig());
    }

    /**
     * Checks that an {@link Actor} has all properties in a field for a specific {@link Property}
     *
     * @param actor    The actor that owns the property
     * @param property The property in the field we're checking
     * @return
     */
    public static boolean hasAllPropertiesInFieldForProperty(Actor actor, Property property) {
        var field = fieldsForProperties.get(property.getPropertyConfig());
        var matchingProperties =
            Arrays.stream(actor.getInventory().getTypeInInventory(Property.class))
                .map(Property::getPropertyConfig)
                .filter(pc -> ArrayUtils.contains(field.getPropertyConfigs(), pc))
                .collect(Collectors.toList());

        var fieldPropertyConfigs = Arrays.asList(field.getPropertyConfigs());

        return new HashSet<>(matchingProperties).containsAll(fieldPropertyConfigs);
    }
}
