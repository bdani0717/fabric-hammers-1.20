package org.spysat.simplehammers.item;

import draylar.magna.item.HammerItem; //this is a bit confusing but it is unfortunately necessary unless i rewrite the API
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spysat.simplehammers.SimpleHammers;

public class ImpactHammerItem extends HammerItem {

    public static Item STONE_IMPACT_HAMMER = registerImpactHammer("copper_impact_hammer", new ImpactHammerItem(ToolMaterials.STONE, 3, 1.15F, new Item.Settings(), 1));
    public static Item IRON_IMPACT_HAMMER = registerImpactHammer("iron_impact_hammer", new ImpactHammerItem(ToolMaterials.IRON, 3, 1.7F, new Item.Settings(), 1));
    public static Item GOLD_IMPACT_HAMMER = registerImpactHammer("gold_impact_hammer", new ImpactHammerItem(ToolMaterials.GOLD, 3, 3.5F, new Item.Settings(), 1));
    public static Item DIAMOND_IMPACT_HAMMER = registerImpactHammer("diamond_impact_hammer", new ImpactHammerItem(ToolMaterials.DIAMOND, 3, 2.3F, new Item.Settings(), 2));
    public static Item NETHERITE_IMPACT_HAMMER = registerImpactHammer("netherite_impact_hammer", new ImpactHammerItem(ToolMaterials.NETHERITE, 3, 2.6F, new Item.Settings(), 2));

    public ImpactHammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, int breakRadius) {
        super(toolMaterial, attackDamage, attackSpeed, settings, breakRadius);
    }

    private static Item registerImpactHammer(String name, ImpactHammerItem item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Impact Hammer items...");
    }
}
