package net.w.fabrichammers.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.w.fabrichammers.FabricHammers;
import net.w.fabrichammers.config.ConfigProvider;
import net.w.fabrichammers.material.HammerMaterial;
import net.w.fabrichammers.util.AoeTool;

public class HammerItem extends PickaxeItem implements AoeTool {
    public static Item WOODEN_HAMMER;
    public static Item STONE_HAMMER;
    public static Item IRON_HAMMER;
    public static Item GOLDEN_HAMMER;
    public static Item EMERALD_HAMMER;
    public static Item DIAMOND_HAMMER;
    public static Item NETHERITE_HAMMER;

    private final int miningRadius;

    public HammerItem(String id) {
        super(HammerMaterial.fromConfig(id), 1, -3.2F, new Item.Settings());
        this.miningRadius = ConfigProvider.CONFIG.getMiningRadius(id);
    }

    private static Item registerHammer(String id) {
        Item hammer = Registry.register(Registries.ITEM, new Identifier(FabricHammers.MOD_ID, id), new HammerItem(id));
        addHammerToToolsGroup(hammer);

        return hammer;
    }

    private static void addHammerToToolsGroup(Item hammer) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(hammer);
        });
    }

    public static void registerHammers() {
        WOODEN_HAMMER = registerHammer("wooden_hammer");
        STONE_HAMMER = registerHammer("stone_hammer");
        IRON_HAMMER = registerHammer("iron_hammer");
        GOLDEN_HAMMER = registerHammer("golden_hammer");
        EMERALD_HAMMER = registerHammer("emerald_hammer");
        DIAMOND_HAMMER = registerHammer("diamond_hammer");
        NETHERITE_HAMMER = registerHammer("netherite_hammer");
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
