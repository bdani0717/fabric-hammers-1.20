package org.spysat.simplehammers.config;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static net.minecraft.registry.Registries.BLOCK;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class Config {
    private class HammerRecipeConfig {
        private HashMap<String, String> recipeList;
        public HammerRecipeConfig(HashMap<String, String> recipeList) {
            this.recipeList = recipeList;
        }

        public HammerRecipeConfig() {}

        private static HashMap<String, String> defaultRecipeList = new HashMap<>() {{
            put("minecraft:tuff", "minecraft:basalt");
            put("minecraft:granite", "minecraft:dripstone");
            put("minecraft:smooth_stone", "minecraft:stone");
            put("minecraft:stone", "minecraft:cobblestone");
            put("minecraft:cobblestone", "minecraft:gravel");
            put("minecraft:gravel", "minecraft:dirt");
            put("minecraft:dirt", "minecraft:sand");
            put("minecraft:sand", "simplehammers:dust");
        }};

        public static @NotNull HashMap<Block, Block> generateHammeringMap() {
            HashMap<Block, Block> HammeringMap = new HashMap<>();

            defaultRecipeList.forEach((key, value) -> HammeringMap.put(
                    BLOCK.get(new Identifier(key)),
                    BLOCK.get(new Identifier(value))
            ));

            return HammeringMap;
        }
    }
    public static @NotNull HashMap<Block, Block> getHammeringMapFromConfig() {
        return HammerRecipeConfig.generateHammeringMap();
    }
}

// TODO: Rewrite this section at a later date so that I can reference the created HammeringMap outside of the HammerConfig class.
// Right now, everything related to the recipeList is static. That means there is only one copy. This also means, it won't be picked up by
// Gson, the JSON parsing library I'm using. This means that although theoretically, anyone can now add their own hammering recipes, in practice
// It will be impossible unless I figure out how to make these methods referenceable in a static context without unstaticing the stuff in HammerConfig.