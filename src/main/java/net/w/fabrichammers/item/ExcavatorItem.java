package net.w.fabrichammers.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.w.fabrichammers.FabricHammers;
import net.w.fabrichammers.config.ConfigProvider;
import net.w.fabrichammers.material.CustomToolMaterial;
import net.w.fabrichammers.util.AoeTool;

public class ExcavatorItem extends ShovelItem implements AoeTool {
    public static Item WOODEN_EXCAVATOR;
    public static Item STONE_EXCAVATOR;
    public static Item IRON_EXCAVATOR;
    public static Item GOLDEN_EXCAVATOR;
    public static Item EMERALD_EXCAVATOR;
    public static Item DIAMOND_EXCAVATOR;
    public static Item NETHERITE_EXCAVATOR;

    private final int miningRadius;

    public ExcavatorItem(String id) {
        super(CustomToolMaterial.fromConfig(id), 1, -3.2F, new Item.Settings());
        this.miningRadius = ConfigProvider.CONFIG.getMiningRadius(id);
    }

    private static Item registerExcavator(String id) {
        Item excavator = Registry.register(Registries.ITEM, new Identifier(FabricHammers.MOD_ID, id), new ExcavatorItem(id));
        addToToolsGroup(excavator);

        return excavator;
    }

    private static void addToToolsGroup(Item item) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(item);
        });
    }

    public static void registerExcavators() {
        WOODEN_EXCAVATOR = registerExcavator("wooden_excavator");
        STONE_EXCAVATOR = registerExcavator("stone_excavator");
        IRON_EXCAVATOR = registerExcavator("iron_excavator");
        GOLDEN_EXCAVATOR = registerExcavator("golden_excavator");
        DIAMOND_EXCAVATOR = registerExcavator("diamond_excavator");
        NETHERITE_EXCAVATOR = registerExcavator("netherite_excavator");

        if (ConfigProvider.CONFIG.isEnableEmeradlTools()) EMERALD_EXCAVATOR = registerExcavator("emerald_excavator");
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
