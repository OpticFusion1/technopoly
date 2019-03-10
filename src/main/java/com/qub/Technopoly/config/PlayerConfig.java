package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class PlayerConfig {
    private final int minPlayers;
    private final int maxPlayers;
    private final int startBalance;

    // Default Configuration
    public PlayerConfig() {
        minPlayers = 2;
        maxPlayers = 4;
        startBalance = 10000;
    }
}
