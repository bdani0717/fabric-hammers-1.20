package org.spysat.simplehammers.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.spysat.simplehammers.SimpleHammers;
import org.spysat.simplehammers.block.ModBlocks;
import java.util.HashMap;
import java.util.Map;

public class HammerItem extends MiningToolItem {

    public static final TagKey<Block> HAMMERABLES = TagKey.of(RegistryKeys.BLOCK, new Identifier("simplehammers", "hammerables")); //Custom block tag
    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", new HammerItem(1.2f, -3.3f, ToolMaterials.WOOD, HAMMERABLES, new Item.Settings()));
    public static final Item STONE_HAMMER = registerItem("stone_hammer", new HammerItem(1.3f, -3.3f, ToolMaterials.STONE, HAMMERABLES, new Item.Settings()));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", new HammerItem(1.5f, -3.2f, ToolMaterials.IRON, HAMMERABLES, new Item.Settings()));
    public static final Item GOLD_HAMMER = registerItem("gold_hammer", new HammerItem(1.2f, -3.0f, ToolMaterials.GOLD, HAMMERABLES, new Item.Settings()));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", new HammerItem(1.5f, -3.1f, ToolMaterials.DIAMOND, HAMMERABLES, new Item.Settings()));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", new HammerItem(1.6f, -3.1f, ToolMaterials.NETHERITE, HAMMERABLES, new Item.Settings().fireproof()));

    public HammerItem(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Item.Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return state.isIn(BlockTags.PICKAXE_MINEABLE) || state.isIn(BlockTags.SHOVEL_MINEABLE) ? this.miningSpeed : 1.0f;
    }

    @Override
    public boolean isSuitableFor(BlockState state) { //Check the tool material to compare whether it can be mined by the hammer
        if (state.isIn(HAMMERABLES)) {
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

    private static Item registerItem(String name, HammerItem item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Mod Items for " + SimpleHammers.MOD_ID);
    }
}