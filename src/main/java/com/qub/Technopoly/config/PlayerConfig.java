package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Configuration for the {@link com.qub.Technopoly.actor.Player}
 */
@RequiredArgsConstructor
@Value
public class PlayerConfig {
    private final int minPlayers;
    private final int maxPlayers;

    // Default Configuration
    public PlayerConfig() {
        minPlayers = 2;
        maxPlayers = 4;
    }
}
