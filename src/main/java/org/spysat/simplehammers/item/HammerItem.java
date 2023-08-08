package org.spysat.simplehammers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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
import java.util.Set;

public class HammerItem extends MiningToolItem {

    public static final TagKey<Block> HAMMERABLES = TagKey.of(RegistryKeys.BLOCK, new Identifier("simplehammers", "hammerables")); //Custom block tag

    public static Item WOODEN_HAMMER = registerHammer("wooden_hammer", new HammerItem(ToolMaterials.WOOD, 3, 2, HAMMERABLES, new Item.Settings()));
    public static Item STONE_HAMMER = registerHammer("stone_hammer", new HammerItem(ToolMaterials.STONE, 3, 2, HAMMERABLES, new Item.Settings()));
    public static Item IRON_HAMMER = registerHammer("iron_hammer", new HammerItem(ToolMaterials.IRON, 3, 2, HAMMERABLES, new Item.Settings()));
    public static Item GOLD_HAMMER = registerHammer("gold_hammer", new HammerItem(ToolMaterials.GOLD, 3, 2, HAMMERABLES, new Item.Settings()));
    public static Item DIAMOND_HAMMER = registerHammer("diamond_hammer", new HammerItem(ToolMaterials.DIAMOND, 3, 2, HAMMERABLES, new Item.Settings()));
    public static Item NETHERITE_HAMMER = registerHammer("netherite_hammer", new HammerItem(ToolMaterials.NETHERITE, 3, 2, HAMMERABLES, new Item.Settings()));

    public HammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, toolMaterial, effectiveBlocks, settings);
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

    private static Item registerHammer(String name, HammerItem item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Hammer items...");
    }
}