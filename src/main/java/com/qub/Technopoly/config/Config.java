package com.qub.Technopoly.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Config {
    private static Config config;
    @Getter
    private final PlayerConfig playerConfig;
    @Getter
    private final DiceConfig diceConfig;

    public static Config getConfig() {
        if (config == null) {
            // TODO - Load config from disk if exists. If not, create a new instance
            // of default config
            config = new Config(new PlayerConfig(), new DiceConfig());
        }

        return config;
    }
}
