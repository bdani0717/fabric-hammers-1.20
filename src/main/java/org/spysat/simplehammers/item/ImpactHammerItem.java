package org.spysat.simplehammers.item;

import draylar.magna.item.HammerItem; //this is a bit confusing but it is unfortunately necessary unless i rewrite the API
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spysat.simplehammers.SimpleHammers;
import org.spysat.simplehammers.config.ConfigProvider;
import org.spysat.simplehammers.material.CustomToolMaterial;

public class ImpactHammerItem extends HammerItem {
    public static Item STONE_IMPACT_HAMMER;
    public static Item IRON_IMPACT_HAMMER;
    public static Item GOLD_IMPACT_HAMMER;
    public static Item DIAMOND_IMPACT_HAMMER;
    public static Item NETHERITE_IMPACT_HAMMER;

    public ImpactHammerItem(String id) {
        super(CustomToolMaterial.fromConfig(id), 1, -3.2F, new Item.Settings());
    }

    private static Item registerImpactHammer(String id) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, id), new ImpactHammerItem(id));
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Impact Hammer items...");

        STONE_IMPACT_HAMMER = registerImpactHammer("stone_impact_hammer");
        IRON_IMPACT_HAMMER = registerImpactHammer("iron_impact_hammer");
        GOLD_IMPACT_HAMMER = registerImpactHammer("golden_impact_hammer");
        DIAMOND_IMPACT_HAMMER = registerImpactHammer("diamond_impact_hammer");
        NETHERITE_IMPACT_HAMMER = registerImpactHammer("netherite_impact_hammer");
    }
}
