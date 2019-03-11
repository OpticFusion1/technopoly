package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Configuration for the {@link com.qub.Technopoly.tile.Start} Tile
 */
@Value
@RequiredArgsConstructor
public class StartConfig {
    private final String name;
    private final int startPassBonus;
    private final int startLandOnBonus;
}
