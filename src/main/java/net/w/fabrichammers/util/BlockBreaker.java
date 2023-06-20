package net.w.fabrichammers.util;

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
import net.w.fabrichammers.impl.PlayerInteractionManagerExtension;

import java.util.ArrayList;
import java.util.List;

public class BlockBreaker {
    public static void breakInRadius(World world, PlayerEntity player, int radius, BreakValidator breakValidator, BlockProcessor smelter, boolean damageTool) {
        breakInRadius(world, player, radius, 0, breakValidator, smelter, damageTool);
    }

    public static void breakInRadius(World world, PlayerEntity player, int radius, int depth, BreakValidator breakValidator, BlockProcessor smelter, boolean damageTool) {
        breakInRadius(world, player, radius, depth, BlockFinder.DEFAULT, breakValidator, smelter, damageTool);
    }

    public static void breakInRadius(World world, PlayerEntity player, int radius, int depth, BlockFinder finder, BreakValidator breakValidator, BlockProcessor smelter, boolean damageTool) {
        if(!world.isClient) {
            // Flag ServerPlayerInteractionManager as saying we are now breaking in Hammer context.
            // See the large block of comments down below for a more in-depth explanation.
            ServerPlayerInteractionManager interactionManager = ((ServerPlayerEntity) player).interactionManager;
            ((PlayerInteractionManagerExtension) interactionManager).fabrichammers_setMining(true);

            // collect all potential blocks to break and attempt to break them
            List<BlockPos> brokenBlocks = finder.findPositions(world, player, radius, depth);
            for(BlockPos pos : brokenBlocks) {
                BlockState state = world.getBlockState(pos);
                BlockEntity blockEntity = world.getBlockState(pos).hasBlockEntity() ? world.getBlockEntity(pos) : null;

                // ensure the tool or mechanic can break the given state
                if(breakValidator.canBreak(world, pos) && !state.isAir()) {
                    state.getBlock().onBreak(world, pos, state, player);
                    if (!interactionManager.tryBreakBlock(pos)) {
                        continue;
                    }

                    // check FAPI break callback
                    boolean result = PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(world, player, pos, state, world.getBlockEntity(pos));
                    if(!result) {
                        continue;
                    }

                    // The following check is wacky. To start, a Hammer breaking a block is redirected
                    //   into a 3x3 break by ServerPlayerInteractionManagerMixin. At the same time,
                    //   we want to ensure all 3x3 are valid breaks (for things like callbacks or claim protection),
                    //   so we again check tryBreakBlock for validity. To avoid recursively breaking blocks,
                    //   and to cancel the actual block broken logic / item drops, ServerPlayerInteractionManagerMixin
                    //   checks a flag set at the top of this method.
                    // In other words: if this part is reached, only the first 50% of tryBreakBlock was called, and it is a valid break.
                    boolean bl = world.removeBlock(pos, false);
                    if (bl) {
                        state.getBlock().onBroken(world, pos, state);
                    }

                    // only drop items in creative
                    if(!player.isCreative()) {
                        Vec3d offsetPos = new Vec3d(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);

                        // obtain dropped stacks for the given block
                        List<ItemStack> droppedStacks = Block.getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, player, player.getMainHandStack());
                        List<ItemStack> processed = new ArrayList<>();

                        // attempt to process stack for mechanics like autosmelt
                        droppedStacks.forEach(stack -> processed.add(smelter.process(player.getInventory().getMainHandStack(), stack)));

                        // drop items
                        dropItems(player, world, processed, offsetPos);
                        state.onStacksDropped((ServerWorld) world, pos, player.getMainHandStack(), true);

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
            ((PlayerInteractionManagerExtension) (interactionManager)).fabrichammers_setMining(false);
        }
    }

    private static void dropItems(PlayerEntity player, World world, List<ItemStack> stacks, Vec3d pos) {
        for(ItemStack stack : stacks) {
            // The stack passed in to insertStack is mutated, so we can operate on it here without worrying about duplicated items.
            if (!stack.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                world.spawnEntity(itemEntity);
            }
        }
    }

    private BlockBreaker() {
        // NO-OP
    }
}
