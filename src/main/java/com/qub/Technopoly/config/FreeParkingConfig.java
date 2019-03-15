package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Configuration for the {@link com.qub.Technopoly.tile.FreeParking} Tile
 */
@Value
@RequiredArgsConstructor
public class FreeParkingConfig {
    private final String name;
    private final int tilePosition;
}
