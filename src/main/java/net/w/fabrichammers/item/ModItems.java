package net.w.fabrichammers.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.w.fabrichammers.FabricHammers;
import net.w.fabrichammers.config.ConfigProvider;
import net.w.fabrichammers.material.CustomToolMaterial;
import draylar.magna.item.HammerItem;

public class ModItems extends HammerItem {
    public static Item WOODEN_HAMMER;
    public static Item STONE_HAMMER;
    public static Item IRON_HAMMER;
    public static Item GOLDEN_HAMMER;
    public static Item EMERALD_HAMMER;
    public static Item DIAMOND_HAMMER;
    public static Item NETHERITE_HAMMER;

    private final int miningRadius;

    public ModItems(String id) {
        super(CustomToolMaterial.fromConfig(id), 1, -3.2F, new Item.Settings());
        this.miningRadius = ConfigProvider.CONFIG.getMiningRadius(id);
    }

    private static Item registerHammer(String id) {
        Item hammer = Registry.register(Registries.ITEM, new Identifier(FabricHammers.MOD_ID, id), new ModItems(id));
        addToToolsGroup(hammer);

        return hammer;
    }

    private static void addToToolsGroup(Item item) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(item);
        });
    }

    public static void registerHammers() {
        WOODEN_HAMMER = registerHammer("wooden_hammer");
        STONE_HAMMER = registerHammer("stone_hammer");
        IRON_HAMMER = registerHammer("iron_hammer");
        GOLDEN_HAMMER = registerHammer("golden_hammer");
        DIAMOND_HAMMER = registerHammer("diamond_hammer");
        NETHERITE_HAMMER = registerHammer("netherite_hammer");

        if (ConfigProvider.CONFIG.isEnableEmeradlTools()) EMERALD_HAMMER = registerHammer("emerald_hammer");
    }

    @Override
    public int getRadius(ItemStack stack) {
        return this.miningRadius;
    }

    @Override
    public boolean playBreakEffects() {
        return false;
    }
}
