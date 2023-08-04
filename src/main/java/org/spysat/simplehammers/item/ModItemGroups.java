package org.spysat.simplehammers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import net.minecraft.util.Identifier;
import org.spysat.simplehammers.SimpleHammers;
import org.spysat.simplehammers.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup HAMMER_GROUP =  Registry.register(Registries.ITEM_GROUP, new Identifier(SimpleHammers.MOD_ID, "items"),
            FabricItemGroup.builder()
            .icon(() -> new ItemStack(HammerItem.DIAMOND_HAMMER))
            .displayName(Text.translatable("itemGroup.simpleHammers.items"))
            .entries((enabledFeatures, entries) -> {
                entries.add(HammerItem.WOODEN_HAMMER);
                entries.add(HammerItem.STONE_HAMMER);
                entries.add(HammerItem.IRON_HAMMER);
                entries.add(HammerItem.GOLD_HAMMER);
                entries.add(HammerItem.DIAMOND_HAMMER);
                entries.add(HammerItem.NETHERITE_HAMMER);

                entries.add(ImpactHammerItem.WOODEN_IMPACT_HAMMER);
                entries.add(ImpactHammerItem.STONE_IMPACT_HAMMER);
                entries.add(ImpactHammerItem.IRON_IMPACT_HAMMER);
                entries.add(ImpactHammerItem.GOLD_IMPACT_HAMMER);
                entries.add(ImpactHammerItem.DIAMOND_IMPACT_HAMMER);
                entries.add(ImpactHammerItem.NETHERITE_IMPACT_HAMMER);

                entries.add(ModBlocks.DUST);
            })
            .build());
    public static void registerItemGroups() {
        SimpleHammers.LOGGER.info("Registering Item Groups for " + SimpleHammers.MOD_ID);
    }
}
