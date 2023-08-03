package org.spysat.simplehammers;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spysat.simplehammers.item.HammerItem;
//import org.spysat.simplehammers.registry.SHRegistry;
import net.fabricmc.api.ModInitializer;


import java.util.logging.Logger;

public class SimpleHammers implements ModInitializer {
    //Eventually, I'd like to move all the definitions of in-game items to their own class file
    public static final TagKey<Block> HAMMERABLES = TagKey.of(RegistryKeys.BLOCK, new Identifier("simplehammers", "hammerables")); //Custom block tag

    //Define items before initialisation
    public static final HammerItem WOODEN_HAMMER = new HammerItem(1.2f, -3.3f, ToolMaterials.WOOD, HAMMERABLES, new Item.Settings());
    public static final HammerItem STONE_HAMMER = new HammerItem(1.3f, -3.3f, ToolMaterials.STONE, HAMMERABLES, new Item.Settings());
    public static final HammerItem IRON_HAMMER = new HammerItem(1.5f, -3.2f, ToolMaterials.IRON, HAMMERABLES, new Item.Settings());
    public static final HammerItem GOLD_HAMMER = new HammerItem(1.2f, -3.0f, ToolMaterials.GOLD, HAMMERABLES, new Item.Settings());
    public static final HammerItem DIAMOND_HAMMER = new HammerItem(1.5f, -3.1f, ToolMaterials.DIAMOND, HAMMERABLES, new Item.Settings());
    public static final HammerItem NETHERITE_HAMMER = new HammerItem(1.6f, -3.1f, ToolMaterials.NETHERITE, HAMMERABLES, new Item.Settings().fireproof());
    public static final ItemGroup HAMMER_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(DIAMOND_HAMMER))
            .displayName(Text.translatable("itemGroup.simpleHammers.items"))
            .entries((enabledFeatures, entries) -> {
                entries.add(WOODEN_HAMMER);
                entries.add(STONE_HAMMER);
                entries.add(IRON_HAMMER);
                entries.add(GOLD_HAMMER);
                entries.add(DIAMOND_HAMMER);
                entries.add(NETHERITE_HAMMER);
            })
            .build();
    @Override
    public void onInitialize() {
        Logger.getLogger("SimpleHammers").info(HAMMERABLES.toString());
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "wooden_hammer"), WOODEN_HAMMER);
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "stone_hammer"), STONE_HAMMER);
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "iron_hammer"), IRON_HAMMER);
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "gold_hammer"), GOLD_HAMMER);
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "diamond_hammer"), DIAMOND_HAMMER);
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "netherite_hammer"), NETHERITE_HAMMER);
        Registry.register(Registries.ITEM_GROUP, new Identifier("simplehammers", "items"), HAMMER_GROUP);
//      SHRegistry.init();
    }
}
