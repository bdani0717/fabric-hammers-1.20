package org.spysat.simplehammers;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class SimpleHammers implements ModInitializer {
    public static final TagKey<Block> HAMMER_TAG = TagKey.of(RegistryKeys.BLOCK, new Identifier("simplehammers", "hammer_tag"));
    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("simplehammers", "wooden_hammer"),
                new HammerItem(1.0f, -3.1f, ToolMaterials.WOOD, HAMMER_TAG, new Item.Settings())
        );
    }
}
