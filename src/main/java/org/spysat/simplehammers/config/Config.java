package org.spysat.simplehammers.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class Config {
    private class HammerConfig {
        public class Recipe {
            private String input;
            private String output;
        }

        List<Recipe> recipeList;
        private float miningSpeedMultiplier;

        public HammerConfig() {}

        public HammerConfig(List<Recipe> recipeList, float miningSpeedMultiplier) {
            this.recipeList = recipeList;
            this.miningSpeedMultiplier = miningSpeedMultiplier;
        }

        public HashMap<Block, Block> generateHammeringMap(String t){ //Generate the HammeringMap (hammering recipes) dynamically from the simplehammers.json config file, specifically the "recipes" section
            Gson gson = new Gson();

            HashMap<Block, Block> HammeringMap = new HashMap<>();
            
            return HammeringMap;
        };
    }
}
