package org.spysat.simplehammers.config;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;

import java.util.HashMap;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class Config {
    private class HammerConfig {
        private float miningSpeedMultiplier;
        public HammerConfig(float miningSpeedMultiplier) {
            this.miningSpeedMultiplier = miningSpeedMultiplier;
        }

        public HashMap<Block, Block> generateHammeringMap(String t){ //Generate the HammeringMap (hammering recipes) dynamically from the simplehammers.json config file, specifically the "recipes" section
            HashMap<Block, Block> HammeringMap = new HashMap<>();    //Take in the key and value as Registry.BLOCK.get(new Identifier(textValue)) and print an ItemNotFound Exception if the item is not found.

            return HammeringMap;
        };

        public JsonObject generateMineableTags(String t){ // Reading from the simplehammers recipe.json config file, re-generate the mineable block tags on each run. Do this by sorting through the recipes and removing any duplicate blocks.
            JsonObject hammerableBlockTags = new JsonObject();

            return hammerableBlockTags;
        }
    }
}
