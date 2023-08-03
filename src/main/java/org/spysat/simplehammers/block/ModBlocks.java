package org.spysat.simplehammers.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import net.minecraft.sound.BlockSoundGroup;


public class ModBlocks {
    public static final Block DUST = new Block(FabricBlockSettings.create().strength(0.3f).sounds(BlockSoundGroup.SAND));
}