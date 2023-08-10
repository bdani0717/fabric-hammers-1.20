package org.spysat.simplehammers;

import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;
import org.spysat.simplehammers.block.ModBlocks;
import org.spysat.simplehammers.item.HammerItem;
import org.spysat.simplehammers.item.ImpactHammerItem;
import org.spysat.simplehammers.item.ModItemGroup;
import org.spysat.simplehammers.config.ConfigProvider;

public class SimpleHammers implements ModInitializer {
    public static final String MOD_ID = "simplehammers";
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Loading Simple Hammers...");

        ConfigProvider.loadConfig();

        ModItemGroup.registerItemGroups();
        HammerItem.registerModItems();
        ImpactHammerItem.registerModItems();
        ModBlocks.registerModBlocks();

        //TODO: Add JEI plugin for Hammering recipes by reading the HammeringMap and Jade plugin for Hammerability of blocks by reading the hammerables block tags
    }
}
