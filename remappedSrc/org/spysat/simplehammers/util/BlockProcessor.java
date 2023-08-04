package org.spysat.simplehammers.util;

import net.minecraft.item.ItemStack;

public interface BlockProcessor {
    ItemStack process(ItemStack tool, ItemStack input);
}
