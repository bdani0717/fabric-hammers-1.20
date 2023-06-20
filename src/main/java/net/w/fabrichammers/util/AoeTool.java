package net.w.fabrichammers.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.w.fabrichammers.event.ToolRadiusCallback;

public interface AoeTool {
    int getRadius(ItemStack stack);

    default int getDepth(ItemStack stack) {
        return 0;
    }

    boolean playBreakEffects();

    default BlockPos getCenterPosition(World world, PlayerEntity player, BlockHitResult blockHitResult, ItemStack toolStack) {
        return blockHitResult.getBlockPos();
    }

    default BlockProcessor getProcessor(World world, PlayerEntity player, BlockPos pos, ItemStack heldStack) {
        return (tool, input) -> input;
    }

    default boolean isBlockValidForBreaking(BlockView view, BlockPos pos, ItemStack stack) {
        BlockState blockState = view.getBlockState(pos);

        if (blockState.getHardness(view, pos) == -1.0) {
            return false;
        }

        if (stack.isSuitableFor(blockState)) {
            return true;
        }

        if (blockState.isToolRequired()) {
            return false;
        }

        return stack.getMiningSpeedMultiplier(blockState) > 1.0F;
    }

    default boolean attemptBreak(World world, BlockPos pos, PlayerEntity player, int breakRadius, BlockProcessor processor) {
        ItemStack stack = player.getMainHandStack();
        if (ignoreRadiusBreak(stack, player)) {
            return false;
        }

        // only do a 3x3 break if the player's tool is effective on the block they are breaking
        // this makes it so breaking gravel doesn't break nearby stone
        if (isBlockValidForBreaking(world, pos, stack)) {
            int radius = ToolRadiusCallback.EVENT.invoker().getRadius(stack, breakRadius);
            int depth = getDepth(stack);

            // break blocks
            BlockBreaker.breakInRadius(world, player, radius, depth, getBlockFinder(), (view, breakPos) -> isBlockValidForBreaking(view, breakPos, stack), processor, true);
            return true;
        }

        return false;
    }

    default boolean ignoreRadiusBreak(ItemStack stack, PlayerEntity player) {
        return player.isSneaking();
    }

    default boolean renderOutline(World world, BlockHitResult ray, PlayerEntity player, ItemStack stack) {
        return true;
    }

    default BlockFinder getBlockFinder() {
        return BlockFinder.DEFAULT;
    }

    default boolean showExtendedOutline(ItemStack stack, PlayerEntity player) {
        return !player.isSneaking();
    }
}
