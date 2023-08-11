package org.spysat.simplehammers.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import org.spysat.simplehammers.SimpleHammers;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path configPath = FabricLoader.getInstance().getConfigDir();
    private static final File configFile = new File(configPath + File.separator + "simplehammers.json");
    public static Config CONFIG;

    // Method to load and handle configuration settings
    public static void loadConfig() {
        SimpleHammers.LOGGER.info("Loading configs...");

        try {
            // Check if the configuration file exists
            if (configFile.createNewFile()) {
                SimpleHammers.LOGGER.info("Creating default config.");

                // Create and save a default configuration
                String json = GSON.toJson(JsonParser.parseString(GSON.toJson(new Config())));
                try (PrintWriter out = new PrintWriter(configFile)) {
                    out.println(json);
                }

                // Set CONFIG to the default configuration
                CONFIG = new Config();
                SimpleHammers.LOGGER.info("Successfully created default config.");
            } else {
                SimpleHammers.LOGGER.info("Config file was found, loading it...");

                // Load the existing configuration from the file
                CONFIG = GSON.fromJson(new String(Files.readAllBytes(configFile.toPath())), Config.class);

                // Check if the loaded configuration is null
                if(CONFIG == null) {
                    throw new NullPointerException("[" + SimpleHammers.MOD_ID + "] The config file was empty.");
                } else {
                    SimpleHammers.LOGGER.info("Successfully loaded config file.");
                }
            }
        } catch (Exception exception) {
            // Handle errors during the configuration loading process
            SimpleHammers.LOGGER.error("There was an error creating/loading the config file!", exception);

            // Set CONFIG to a default configuration and log a warning
            CONFIG = new Config();
            SimpleHammers.LOGGER.warn("Defaulting to original config.");
        }
    }
}