package org.spysat.simplehammers.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spysat.simplehammers.event.ToolRadiusCallback;

// This interface defines methods for custom tools with Area of Effect (AOE) capabilities.
public interface AoeTool {

    // Get the mining radius of the tool.
    int getRadius(ItemStack stack);

    // Get the depth for AOE mining.
    default int getDepth(ItemStack stack) {
        return 0;
    }

    // Determine whether to play break effects when mining.
    boolean playBreakEffects();

    // Get the center position for AOE mining.
    default BlockPos getCenterPosition(World world, PlayerEntity player, BlockHitResult blockHitResult, ItemStack toolStack) {
        return blockHitResult.getBlockPos();
    }

    // Get a processor to modify blocks during AOE mining.
    default BlockProcessor getProcessor(World world, PlayerEntity player, BlockPos pos, ItemStack heldStack) {
        return (tool, input) -> input;
    }

    // Check if a block is valid for breaking during AOE mining.
    default boolean isBlockValidForBreaking(BlockView view, BlockPos pos, ItemStack stack) {
        BlockState blockState = view.getBlockState(pos);

        // Check if the block is unbreakable.
        if (blockState.getHardness(view, pos) == -1.0) {
            return false;
        }

        // Check if the tool is suitable for breaking this block.
        if (stack.isSuitableFor(blockState)) {
            return true;
        }

        // Check if the block requires a specific tool.
        if (blockState.isToolRequired()) {
            return false;
        }

        // Check if the tool's mining speed is effective on the block.
        return stack.getMiningSpeedMultiplier(blockState) > 1.0F;
    }

    // Attempt to break blocks in the AOE.
    default boolean attemptBreak(World world, BlockPos pos, PlayerEntity player, int breakRadius, BlockProcessor processor) {
        ItemStack stack = player.getMainHandStack();

        // Check if radius breaking should be ignored (e.g., when sneaking).
        if (ignoreRadiusBreak(stack, player)) {
            return false;
        }

        // Check if the block is valid for breaking.
        if (isBlockValidForBreaking(world, pos, stack)) {
            int radius = ToolRadiusCallback.EVENT.invoker().getRadius(stack, breakRadius);
            int depth = getDepth(stack);

            // Break blocks in the specified radius and depth.
            BlockBreaker.breakInRadius(world, player, radius, depth, getBlockFinder(),
                    (view, breakPos) -> isBlockValidForBreaking(view, breakPos, stack), processor, true);
            return true;
        }

        return false;
    }

    // Check if radius breaking should be ignored (e.g., when sneaking).
    default boolean ignoreRadiusBreak(ItemStack stack, PlayerEntity player) {
        return player.isSneaking();
    }

    // Determine whether to render an outline during AOE mining.
    default boolean renderOutline(World world, BlockHitResult ray, PlayerEntity player, ItemStack stack) {
        return true;
    }

    // Get a block finder for AOE mining.
    default BlockFinder getBlockFinder() {
        return BlockFinder.DEFAULT;
    }

    // Determine whether to show an extended outline during AOE mining.
    default boolean showExtendedOutline(ItemStack stack, PlayerEntity player) {
        return !player.isSneaking();
    }
}
