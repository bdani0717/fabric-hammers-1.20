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

public class HammerItemGroup {
    private static final ItemGroup FABRIC_HAMMERS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(HammerItem.DIAMOND_HAMMER))
            .displayName(Text.translatable("itemgroup.fabrichammers"))
            .entries((displayContext, entries) -> {
                entries.add(HammerItem.WOODEN_HAMMER);
                entries.add(HammerItem.STONE_HAMMER);
                entries.add(HammerItem.IRON_HAMMER);
                entries.add(HammerItem.GOLDEN_HAMMER);
                entries.add(HammerItem.DIAMOND_HAMMER);
                entries.add(HammerItem.NETHERITE_HAMMER);

                entries.add(ExcavatorItem.WOODEN_EXCAVATOR);
                entries.add(ExcavatorItem.STONE_EXCAVATOR);
                entries.add(ExcavatorItem.IRON_EXCAVATOR);
                entries.add(ExcavatorItem.GOLDEN_EXCAVATOR);
                entries.add(ExcavatorItem.DIAMOND_EXCAVATOR);
                entries.add(ExcavatorItem.NETHERITE_EXCAVATOR);

                if (ConfigProvider.CONFIG.isEnableEmeraldTools()) {
                    entries.add(HammerItem.EMERALD_HAMMER);
                    entries.add(ExcavatorItem.EMERALD_EXCAVATOR);
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
