package org.spysat.simplehammers.util;

import net.minecraft.block.Block;
import org.spysat.simplehammers.config.Config;

import java.util.AbstractMap;
import java.util.HashMap;

public class HammerRecipeSerialization {
    public static HashMap<Block, Block> getHammeringMap(){
        Config.HammerConfig config;
        HashMap<Block, Block> hammeringMap = new HashMap<>();
        int i = 0;

        //HammerRecipe -> <String, String> -> <Block, Block>

        //While getRecipeAtIndex returns a value
            //For each element in Recipe, convert from String to Block
                //Put inputBlock and outputBlock (each element) into HammeringMap as a key, value pair
            //Add 1 to i

        //return HammeringMap
        return null;
    }

    public AbstractMap.SimpleEntry<Block, Block> getRecipeFromMap (HashMap<Block, Block> map){
        return null;

        //Return a single key pair from HammeringMap.
    }
}

//TODO - Generate the Hammering Map from the Config's recipeList. The HammeringMap is a HashMap of type <Block, Block> which is used in the BlockHarvestMixin