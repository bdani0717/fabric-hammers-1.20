package org.spysat.simplehammers.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spysat.simplehammers.impl.PlayerInteractionManagerExtension;

import java.util.ArrayList;
import java.util.List;

// This utility class handles breaking blocks in a specified radius around a player.
public class BlockBreaker {

    // Break blocks in a specified radius around a player.
//    public static void breakInRadius(World world, PlayerEntity player, int radius, BreakValidator breakValidator, BlockProcessor smelter, boolean damageTool) {
//        breakInRadius(world, player, radius, 0, breakValidator, smelter, damageTool);
//    }
//
//    // Break blocks in a specified radius and depth around a player.
//    public static void breakInRadius(World world, PlayerEntity player, int radius, int depth, BreakValidator breakValidator, BlockProcessor smelter, boolean damageTool) {
//        breakInRadius(world, player, radius, depth, BlockFinder.DEFAULT, breakValidator, smelter, damageTool);
//    }

    // Break blocks in a specified radius, depth, and using a specific block finder around a player.
    public static void breakInRadius(World world, PlayerEntity player, int radius, int depth, BlockFinder finder, BreakValidator breakValidator, BlockProcessor smelter, boolean damageTool) {
        if (!world.isClient) {
            // Flag ServerPlayerInteractionManager to indicate breaking in Hammer context.
            ServerPlayerInteractionManager interactionManager = ((ServerPlayerEntity) player).interactionManager;
            ((PlayerInteractionManagerExtension) interactionManager).fabrichammers_setMining(true);

            // Collect potential blocks to break and attempt to break them.
            List<BlockPos> brokenBlocks = finder.findPositions(world, player, radius, depth);
            for (BlockPos pos : brokenBlocks) {
                BlockState state = world.getBlockState(pos);
                BlockEntity blockEntity = world.getBlockState(pos).hasBlockEntity() ? world.getBlockEntity(pos) : null;

                // Ensure the tool or mechanic can break the given state.
                if (breakValidator.canBreak(world, pos) && !state.isAir()) {
                    state.getBlock().onBreak(world, pos, state, player);
                    if (!interactionManager.tryBreakBlock(pos)) {
                        continue;
                    }

                    // Check FAPI break callback.
                    boolean result = PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(world, player, pos, state, world.getBlockEntity(pos));
                    if (!result) {
                        continue;
                    }

                    boolean bl = world.removeBlock(pos, false);
                    if (bl) {
                        state.getBlock().onBroken(world, pos, state);
                    }

                    // Only drop items in non-creative mode.
                    if (!player.isCreative()) {
                        Vec3d offsetPos = new Vec3d(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);

                        // Obtain dropped stacks for the given block.
                        List<ItemStack> droppedStacks = Block.getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, player, player.getMainHandStack());
                        List<ItemStack> processed = new ArrayList<>();

                        // Process stacks for mechanics like smelting.
                        droppedStacks.forEach(stack -> processed.add(smelter.process(player.getInventory().getMainHandStack(), stack)));

                        // Drop items.
                        dropItems(player, world, processed, offsetPos);
                        state.onStacksDropped((ServerWorld) world, pos, player.getMainHandStack(), true);

                        // Damage tool and gain statistics.
                        if (damageTool) {
                            ItemStack itemStack = player.getMainHandStack();
                            boolean usingEffectiveTool = player.canHarvest(state);
                            itemStack.postMine(world, state, pos, player);
                            if (usingEffectiveTool) {
                                player.incrementStat(Stats.MINED.getOrCreateStat(state.getBlock()));
                                player.addExhaustion(0.005F);
                            }
                        }
                    }
                }
            }
            // Reset the Hammer context flag.
            ((PlayerInteractionManagerExtension) (interactionManager)).fabrichammers_setMining(false);
        }
    }

    // Drop items into the world at a specified position.
    private static void dropItems(PlayerEntity player, World world, List<ItemStack> stacks, Vec3d pos) {
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                world.spawnEntity(itemEntity);
            }
        }
    }

    // Private constructor to prevent instantiation.
    private BlockBreaker() {
        // NO-OP
    }
}
