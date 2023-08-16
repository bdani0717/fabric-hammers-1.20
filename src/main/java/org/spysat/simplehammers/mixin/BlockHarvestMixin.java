package org.spysat.simplehammers.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spysat.simplehammers.item.HammerItem;
import org.spysat.simplehammers.util.HammerRecipeSerialization;

import java.util.List;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class BlockHarvestMixin {
    @Inject(at = @At("RETURN"), method = "getDroppedStacks", cancellable = true)
    public void getDroppedStacks(LootContextParameterSet.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
        ItemStack tool = builder.get(LootContextParameters.TOOL);
        if (tool.getItem() instanceof HammerItem) {
            Block input = builder.get(LootContextParameters.BLOCK_STATE).getBlock();
            Block output;
            if((output = HammerRecipeSerialization.getHammeringMap().get(input)) != null) {
                cir.setReturnValue(List.of(new ItemStack(output)));
            }
        }
    }
}
