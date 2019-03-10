package com.qub.Technopoly.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerConfig {
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private static PlayerConfig DEFAULT = new PlayerConfig(MIN_PLAYERS, MAX_PLAYERS);

    private final int minPlayers;
    private final int maxPlayers;
}
