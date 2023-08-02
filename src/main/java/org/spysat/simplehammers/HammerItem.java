package org.spysat.simplehammers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class HammerItem extends MiningToolItem {
    public HammerItem(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Item.Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    @Override // Hammers can mine anything that shovels and pickaxes can
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return state.isIn(BlockTags.PICKAXE_MINEABLE) || state.isIn(BlockTags.SHOVEL_MINEABLE) ? this.miningSpeed : 1.0f;
    }
    @Override
    public boolean isSuitableFor(BlockState state) { //Check the tool material to compare whether it can be mined by the hammer
        if (state.isIn(BlockTags.PICKAXE_MINEABLE) || state.isIn(BlockTags.SHOVEL_MINEABLE)) {
            int i = this.getMaterial().getMiningLevel();
            if (i < 3 && state.isIn(BlockTags.NEEDS_DIAMOND_TOOL)) {
                return false;
            } else if (i < 2 && state.isIn(BlockTags.NEEDS_IRON_TOOL)) {
                return false;
            } else {
                return i < 1 && state.isIn(BlockTags.NEEDS_STONE_TOOL);
            }
        }
        return false;
    }
}