package org.spysat.simplehammers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.LoggerFactory;
import org.spysat.simplehammers.block.ModBlocks;
import org.spysat.simplehammers.config.ConfigProvider;
import org.spysat.simplehammers.item.HammerItem;
import org.spysat.simplehammers.item.ImpactHammerItem;
import org.spysat.simplehammers.item.ModItemGroups;

public class SimpleHammers implements ModInitializer {
    public static final String MOD_ID = "simplehammers";
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ConfigProvider.loadOrcreate();
        ModItemGroups.registerItemGroups();

        HammerItem.registerModItems();
        ImpactHammerItem.registerModItems();
        ModBlocks.registerModBlocks();
        FuelRegistry.INSTANCE.add(ImpactHammerItem.WOODEN_IMPACT_HAMMER, 100);
        FuelRegistry.INSTANCE.add(HammerItem.WOODEN_HAMMER, 50);
    }

    /*TODO:
    - Make dust mineable with just your hands
    - Write Impact Hammer logic (extends PickaxeItem, mines in a configurable radius, probably needs a Mixin)
    - Add Copper tool material
    - Add recipes for Impact Hammers
    */
}
