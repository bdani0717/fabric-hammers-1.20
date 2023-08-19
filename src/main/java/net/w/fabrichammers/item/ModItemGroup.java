package net.w.fabrichammers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.w.fabrichammers.FabricHammers;
import net.w.fabrichammers.config.ConfigProvider;

public class ModItemGroup {
    private static final ItemGroup FABRIC_HAMMERS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.DIAMOND_HAMMER))
            .displayName(Text.translatable("itemgroup.fabrichammers"))
            .entries((displayContext, entries) -> {
                entries.add(ModItems.WOODEN_HAMMER);
                entries.add(ModItems.STONE_HAMMER);
                entries.add(ModItems.IRON_HAMMER);
                entries.add(ModItems.GOLDEN_HAMMER);
                entries.add(ModItems.DIAMOND_HAMMER);
                entries.add(ModItems.NETHERITE_HAMMER);

                if (ConfigProvider.CONFIG.isEnableEmeradlTools()) {
                    entries.add(ModItems.EMERALD_HAMMER);
                    entries.add(EmeraldToolItem.EMERALD_SWORD);
                    entries.add(EmeraldToolItem.EMERALD_PICKAXE);
                    entries.add(EmeraldToolItem.EMERALD_AXE);
                    entries.add(EmeraldToolItem.EMERALD_SHOVEL);
                    entries.add(EmeraldToolItem.EMERALD_HOE);
                }
            })
            .build();

    public static void registerItemGroup() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(FabricHammers.MOD_ID, "fabric_hammers_group"), FABRIC_HAMMERS);
    }
}
