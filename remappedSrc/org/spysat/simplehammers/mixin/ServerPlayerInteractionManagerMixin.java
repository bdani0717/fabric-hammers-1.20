package org.spysat.simplehammers.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spysat.simplehammers.impl.PlayerInteractionManagerExtension;
import org.spysat.simplehammers.util.AoeTool;

@Mixin(value = ServerPlayerInteractionManager.class, priority = 1001)
public class ServerPlayerInteractionManagerMixin implements PlayerInteractionManagerExtension {
	@Final
	@Shadow protected ServerPlayerEntity player;
	@Shadow
	protected ServerWorld world;
	@Unique private boolean fabrichammers_isMining = false;

	@Inject(
			method = "tryBreakBlock",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/block/Block;onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V"
			),
			cancellable = true
	)
	private void tryBreak(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		ItemStack heldStack = player.getMainHandStack();

		if (heldStack.getItem() instanceof AoeTool) {
			// This is to avoid recursion, but the goal is to make sure every block it doesn't override cancelled block breaks using Fabric's callbacks. This was made to support claim mods.
			boolean v = fabrichammers_isMining || ((AoeTool) heldStack.getItem()).attemptBreak(world, pos, player, ((AoeTool) heldStack.getItem()).getRadius(heldStack), ((AoeTool) heldStack.getItem()).getProcessor(world, player, pos, heldStack));

			// only cancel if the break was successful (false is returned if the player is sneaking)
			if(v) {
				cir.setReturnValue(true);
			}
		}
	}


    @Override
	public void fabrichammers_setMining(boolean mining) {
		this.fabrichammers_isMining = mining;
	}
}