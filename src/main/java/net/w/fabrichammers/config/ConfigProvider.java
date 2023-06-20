package net.w.fabrichammers.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
                if(CONFIG == null) {
                    throw new NullPointerException("[" + FabricHammers.MOD_ID + "] The config file was empty.");
                }else{
                    FabricHammers.LOGGER.info("Successfully loaded config file.");
                }
            }
        } catch (Exception exception) {
            FabricHammers.LOGGER.error("There was an error creating/loading the config file!", exception);
            CONFIG = new Config();
            FabricHammers.LOGGER.warn("Defaulting to original config.");
        }
    }
}
