package org.spysat.simplehammers.util;

import net.minecraft.block.Block;
import org.spysat.simplehammers.config.Config;

import java.util.ArrayList;
import java.util.HashMap;

public class generateHammeringMap {

    public HashMap<Block, Block> returnHammeringMap(Config config){
        HashMap<Block,Block> HammeringMap = new HashMap<>();

        return HammeringMap;
    };
}

//TODO - Generate the Hammering Map from the Config's recipeList. The HammeringMap is a HashMap of type <Block, Block> which is used in the BlockHarvestMixin