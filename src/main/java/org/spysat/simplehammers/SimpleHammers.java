package org.spysat.simplehammers;

import net.fabricmc.api.ModInitializer;
// import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
// import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.logging.Logger;

public class SimpleHammers implements ModInitializer {

    //Eventually, i'd like to move all the definitions of in-game items to their own class file
    //
    public static final TagKey<Block> HAMMER_TAG = TagKey.of(RegistryKeys.BLOCK, new Identifier("simplehammers", "hammer_tag")); //Custom block tag

    //Define items before initialisation
    public static final HammerItem WOODEN_HAMMER = new HammerItem(1.5f, -3.3f, ToolMaterials.WOOD, HAMMER_TAG, new Item.Settings());
    public static final HammerItem STONE_HAMMER = new HammerItem(1.5f, -3.3f, ToolMaterials.STONE, HAMMER_TAG, new Item.Settings());
    public static final HammerItem IRON_HAMMER = new HammerItem(1.5f, -3.3f, ToolMaterials.IRON, HAMMER_TAG, new Item.Settings());

    @Override
    public void onInitialize() {
        Logger.getLogger("SimpleHammers").info(HAMMER_TAG.toString());
        //Register Items
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "wooden_hammer"), WOODEN_HAMMER);
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "stone_hammer"), STONE_HAMMER);
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "iron_hammer"), IRON_HAMMER);
    }
}
