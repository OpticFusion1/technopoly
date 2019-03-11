package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Configuration for the {@link com.qub.Technopoly.tile.Property} Tile
 */
@Value
@RequiredArgsConstructor
public class PropertyConfig {
    private final String propertyName;
    private final String propertyDescription;
    private final int propertyPrice;
    private final int propertyRent;
    private final HouseConfig[] houseConfigs;

    public PropertyConfig() {
        propertyName = "Property Name";
        propertyDescription = "Property Description";
        propertyPrice = 0;
        propertyRent = 0;
        houseConfigs = new HouseConfig[] {new HouseConfig()};
    }
}
