package org.spysat.simplehammers.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.spysat.simplehammers.SimpleHammers;


public class ModBlocks {
    public static final Block DUST = registerBlock("dust", new Block(FabricBlockSettings.copyOf(Blocks.SAND).sounds(BlockSoundGroup.WOOL).hardness(0.1f)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(SimpleHammers.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        SimpleHammers.LOGGER.info("Registering ModBlocks for " + SimpleHammers.MOD_ID);
    }
}