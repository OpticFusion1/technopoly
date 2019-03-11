package com.qub.Technopoly.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.qub.Technopoly.exception.ConfigurationException;
import com.qub.Technopoly.io.IOHelper;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.qub.Technopoly.io.IOHelper.*;

@RequiredArgsConstructor
@Value
public class Config {
    private static final String CONFIG_FILENAME = "config.json";
    private static Config config;
    private final DelayConfig delayConfig;
    private final InventoryConfig inventoryConfig;
    private final PlayerConfig playerConfig;
    private final DiceConfig diceConfig;
    private final StartConfig startConfig;
    private final FieldConfig[] fieldConfigs;

    public static Config getConfig() {
        if (config != null) {
            return config;
        }

        getOutputSource().writeTitle("Loading Game Configuration");
        getOutputSource().writeBody("Attempting to load configuration from disk...");
        config = getConfigFromDisk();
        if (config != null) {
            getOutputSource().writeBody("Configuration loaded from disk!");
            return config;
        }

        getOutputSource().writeBody("Attempting to load default configuration...");
        config = getConfigFromResources();
        if (config != null) {
            getOutputSource().writeBody("Loaded default configuration!");
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
            getOutputSource().writeBody("Failed loading configuration from disk.");
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
