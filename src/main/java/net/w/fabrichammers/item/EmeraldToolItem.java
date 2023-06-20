package net.w.fabrichammers.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.w.fabrichammers.FabricHammers;
import net.w.fabrichammers.config.ConfigProvider;
import net.w.fabrichammers.material.CustomToolMaterial;

public class EmeraldToolItem {
    public static Item EMERALD_SWORD;
    public static Item EMERALD_PICKAXE;
    public static Item EMERALD_AXE;
    public static Item EMERALD_SHOVEL;
    public static Item EMERALD_HOE;

    public static void registerItems() {
        if (!ConfigProvider.CONFIG.isEnableEmeradlTools()) return;

        EMERALD_SWORD = Registry.register(Registries.ITEM, new Identifier(FabricHammers.MOD_ID, "emerald_sword"),
                new SwordItem(CustomToolMaterial.EMERALD, 3, -2.4F, new Item.Settings()));
        EMERALD_PICKAXE = Registry.register(Registries.ITEM, new Identifier(FabricHammers.MOD_ID, "emerald_pickaxe"),
                new PickaxeItem(CustomToolMaterial.EMERALD, 1, -2.8F, new Item.Settings()));
        EMERALD_AXE = Registry.register(Registries.ITEM, new Identifier(FabricHammers.MOD_ID, "emerald_axe"),
                new AxeItem(CustomToolMaterial.EMERALD, 6, -3.1F, new Item.Settings()));
        EMERALD_SHOVEL = Registry.register(Registries.ITEM, new Identifier(FabricHammers.MOD_ID, "emerald_shovel"),
                new ShovelItem(CustomToolMaterial.EMERALD, 1.5F, -3F, new Item.Settings()));
        EMERALD_HOE = Registry.register(Registries.ITEM, new Identifier(FabricHammers.MOD_ID, "emerald_hoe"),
                new HoeItem(CustomToolMaterial.EMERALD, -2, -1.0F, new Item.Settings()));

        addToCombatGroup(EMERALD_SWORD);
        addToToolsGroup(EMERALD_PICKAXE);
        addToToolsGroup(EMERALD_AXE);
        addToToolsGroup(EMERALD_SHOVEL);
        addToToolsGroup(EMERALD_HOE);
    }

    private static void addToToolsGroup(Item item) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(item);
        });
    }

    private static void addToCombatGroup(Item item) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(item);
        });
    }
}
