package net.w.fabrichammers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.w.fabrichammers.FabricHammers;

public class HammerItemGroup {
    private static final ItemGroup FABRIC_HAMMERS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(HammerItem.EMERALD_HAMMER))
            .displayName(Text.translatable("itemgroup.fabrichammers"))
            .entries((displayContext, entries) -> {
                        entries.add(HammerItem.WOODEN_HAMMER);
                        entries.add(HammerItem.STONE_HAMMER);
                        entries.add(HammerItem.IRON_HAMMER);
                        entries.add(HammerItem.GOLDEN_HAMMER);
                        entries.add(HammerItem.EMERALD_HAMMER);
                        entries.add(HammerItem.DIAMOND_HAMMER);
                        entries.add(HammerItem.NETHERITE_HAMMER);
                    })
            .build();

    public static void registerItemGroup() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(FabricHammers.MOD_ID, "fabric_hammers_group"), FABRIC_HAMMERS);
    }
}
