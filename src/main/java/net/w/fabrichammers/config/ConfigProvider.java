package net.w.fabrichammers.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.w.fabrichammers.FabricHammers;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path configPath = FabricLoader.getInstance().getConfigDir();
    private static final File configFile = new File(configPath + File.separator + "fabrichammers.json");
    public static Config CONFIG;
    private static final String CONFIG_VERSION = "1.3";

    public static void loadOrcreate() {
        FabricHammers.LOGGER.info("Loading configs...");
        try {
            if (configFile.createNewFile()) {
                FabricHammers.LOGGER.info("Creating default config.");
                String json = GSON.toJson(JsonParser.parseString(GSON.toJson(new Config())));
                try (PrintWriter out = new PrintWriter(configFile)) {
                    out.println(json);
                }
                CONFIG = new Config();
                FabricHammers.LOGGER.info("Successfully created default config.");
            } else {
                FabricHammers.LOGGER.info("Config file was found, loading it...");
                CONFIG = GSON.fromJson(new String(Files.readAllBytes(configFile.toPath())), Config.class);

                if (CONFIG == null) {
                    throw new NullPointerException("[" + FabricHammers.MOD_ID + "] The config file was empty.");
                } else if (!isValidConfig()) {
                    FabricHammers.LOGGER.info("Updating config.");

                    updateConfig();
                } else {
                    FabricHammers.LOGGER.info("Successfully loaded config file.");
                }
            }
        } catch (Exception exception) {
            FabricHammers.LOGGER.error("There was an error creating/loading the config file!", exception);
            CONFIG = new Config();
            FabricHammers.LOGGER.warn("Defaulting to original config.");
        }
    }

    private static boolean isValidConfig() {
//        FabricHammers.LOGGER.error(CONFIG.getConfigVersion());
//        FabricHammers.LOGGER.error(CONFIG_VERSION);
//        return CONFIG.getConfigVersion().equals(CONFIG_VERSION);

        try {
            String content = new String(Files.readAllBytes(configFile.toPath()));
            JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();
            if (jsonObject.has("configVersion")) {
                String versionInFile = jsonObject.get("configVersion").getAsString();
                FabricHammers.LOGGER.info("Config version in file: " + versionInFile);
                return versionInFile.equals(CONFIG_VERSION);
            } else {
                FabricHammers.LOGGER.info("configVersion field is missing in the config file.");
                // If the configVersion field is missing, consider the config invalid
                return false;
            }
        } catch (Exception e) {
            FabricHammers.LOGGER.error("Error checking config version: ", e);
            // On exception, consider the config invalid
            return false;
        }
    }

    private static void updateConfig() {
        File oldConfig = new File(configPath + File.separator + "fabrichammers_old.json");
        if (oldConfig.exists() && !oldConfig.delete()) {
            FabricHammers.LOGGER.error("Failed to delete the existing old config file.");
            return;
        }
        configFile.renameTo(oldConfig);

        String json = GSON.toJson(JsonParser.parseString(GSON.toJson(new Config())));
        try (PrintWriter out = new PrintWriter(configFile)) {
            out.println(json);
        } catch (Exception exception) {
            FabricHammers.LOGGER.error("There was an error updating the config file!", exception);
        }
        CONFIG = new Config();
    }
}
