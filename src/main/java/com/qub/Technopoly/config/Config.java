package com.qub.Technopoly.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.qub.Technopoly.exception.ConfigurationException;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Value
public class Config {
    private static final String CONFIG_FILENAME = "config.json";
    private static Config config;
    private final PlayerConfig playerConfig;
    private final DiceConfig diceConfig;
    private final PropertyConfig[] propertyConfigs;

    public static Config getConfig() {
        if (config != null) {
            return config;
        }

        config = getConfigFromDisk();
        if (config != null) {
            return config;
        }


        config = getConfigFromResources();
        if (config != null) {
            return config;
        }

        throw new ConfigurationException("No configuration file was found! Can't start game");
    }

    private static Config getConfigFromDisk() {
        var gson = new Gson();

        Config config = null;
        try (var reader = new JsonReader(new FileReader(CONFIG_FILENAME))) {
            config = gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    private static Config getConfigFromResources() {
        var gson = new Gson();

        Config config = null;
        try (var reader = new JsonReader(new InputStreamReader(
            Config.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME)))) {
            config = gson.fromJson(reader, Config.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
