package org.spysat.simplehammers.integration.jei;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import org.spysat.simplehammers.util.HammerRecipeSerialization;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;

public class HammerRecipe {
    public HammerRecipe(@NotNull final Identifier id, @NotNull AbstractMap.SimpleEntry<Block, Block> recipe){
        //Take in a key value pair from getRecipeFromMap and set the key as the input block and the value as the output block.
    };
}
