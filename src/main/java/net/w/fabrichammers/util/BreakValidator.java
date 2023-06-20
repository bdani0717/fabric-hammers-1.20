package net.w.fabrichammers.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@FunctionalInterface
public interface BreakValidator {
    boolean canBreak(BlockView view, BlockPos pos);
}
