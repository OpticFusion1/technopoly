package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Configuration for the {@link com.qub.Technopoly.tile.Property} Tile.
 * Wraps {@link com.qub.Technopoly.tile.Property} and is fetched by {@link com.qub.Technopoly.util.Field}
 */
@RequiredArgsConstructor
@Value
public class FieldConfig {
    private final String name;
    private final String description;
    private PropertyConfig[] propertyConfigs;

    public FieldConfig() {
        name = "Field Name";
        description = "Field Description";
        propertyConfigs = new PropertyConfig[] {new PropertyConfig()};
    }
}
