package org.spysat.simplehammers.config;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.*;

import static net.minecraft.registry.Registries.BLOCK;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class Config {
    private class HammerConfig {
        public class Recipe {
            private String input;
            private String output;
        }

        public HammerConfig(HashMap<String, String> recipeList, float miningSpeedMultiplier) {
            HammerConfig.recipeList = recipeList;
            this.miningSpeedMultiplier = miningSpeedMultiplier;
        }

        public HammerConfig() {}

        private static HashMap<String, String> recipeList = new HashMap<>() {{
            put("minecraft:tuff", "minecraft:basalt");
            put("minecraft:granite", "minecraft:dripstone");
            put("minecraft:smooth_stone", "minecraft:stone");
            put("minecraft:stone", "minecraft:cobblestone");
            put("minecraft:cobblestone", "minecraft:gravel");
            put("minecraft:gravel", "minecraft:dirt");
            put("minecraft:dirt", "minecraft:sand");
            put("minecraft:sand", "simplehammers:dust");
        }};

        HashMap<String, String> tempRecipeList = recipeList;

        private float miningSpeedMultiplier;


        public static HashMap<Block, Block> generateHammeringMap() {
            HashMap<Block, Block> HammeringMap = new HashMap<>();

            recipeList.forEach((key, value) -> HammeringMap.put(
                    BLOCK.get(new Identifier("simplehammers", key)),
                    BLOCK.get(new Identifier("simplehammers", value))
            ));

            return HammeringMap;
        }
    }

    public static HashMap<Block, Block> getHammeringMapFromConfig() {
        return HammerConfig.generateHammeringMap();
    }
}
