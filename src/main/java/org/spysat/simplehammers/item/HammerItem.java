package org.spysat.simplehammers.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.spysat.simplehammers.SimpleHammers;
import org.spysat.simplehammers.block.ModBlocks;
import org.spysat.simplehammers.material.CustomToolMaterial;

import java.util.HashMap;
import java.util.Map;

public class HammerItem extends MiningToolItem {

    public static Item WOODEN_HAMMER;
    public static Item STONE_HAMMER;
    public static Item IRON_HAMMER;
    public static Item GOLD_HAMMER;
    public static Item DIAMOND_HAMMER;
    public static Item NETHERITE_HAMMER;

    public static final TagKey<Block> MINEABLE_WITH_HAMMER = TagKey.of(RegistryKeys.BLOCK, new Identifier("simplehammers", "mineable_with_hammer.json")); //Custom block tag

    public HammerItem(String id) {
        super(1, -3.2F,  CustomToolMaterial.materialFromConfig(id), MINEABLE_WITH_HAMMER, new Settings());
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return state.isIn(BlockTags.PICKAXE_MINEABLE) || state.isIn(BlockTags.SHOVEL_MINEABLE) ? this.miningSpeed : 1.0f;
    }

    @Override
    public boolean isSuitableFor(BlockState state) { //Check the tool material to compare whether it can be mined by the hammer
        if (state.isIn(MINEABLE_WITH_HAMMER)) {
            return this.getMaterial().getMiningLevel() >= getMiningLevel(state);
        }
        return false;
    }

    public static int getMiningLevel(BlockState state) {
        if (state.isIn(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return ToolMaterials.DIAMOND.getMiningLevel();
        } else if (state.isIn(BlockTags.NEEDS_IRON_TOOL)) {
            return ToolMaterials.IRON.getMiningLevel();
        } else if (state.isIn(BlockTags.NEEDS_STONE_TOOL)) {
            return ToolMaterials.STONE.getMiningLevel();
        }
        return 0;
    }

    public static HashMap<Block, Block> HammeringMap = new HashMap<>( //Map of available interactions. Maybe rewrite this section so that it can be configured by a .json file.
            Map.of
                    (
                            Blocks.STONE, Blocks.COBBLESTONE,
                            Blocks.COBBLESTONE, Blocks.GRAVEL,
                            Blocks.GRAVEL, Blocks.DIRT,
                            Blocks.DIRT, Blocks.SAND,
                            Blocks.SAND, ModBlocks.DUST,
                            ModBlocks.DUST, ModBlocks.DUST
                    )
    );

    private static Item registerHammer(String id) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, id), new HammerItem(id));
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Mod Items for " + SimpleHammers.MOD_ID);
        WOODEN_HAMMER = registerHammer("wooden_hammer");
        STONE_HAMMER = registerHammer("stone_hammer");
        IRON_HAMMER = registerHammer("iron_hammer");
        GOLD_HAMMER = registerHammer("gold_hammer");
        DIAMOND_HAMMER = registerHammer("diamond_hammer");
        NETHERITE_HAMMER = registerHammer("netherite_hammer");
    }
}