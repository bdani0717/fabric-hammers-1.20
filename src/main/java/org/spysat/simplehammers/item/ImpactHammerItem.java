package org.spysat.simplehammers.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spysat.simplehammers.SimpleHammers;
import org.spysat.simplehammers.config.ConfigProvider;
import org.spysat.simplehammers.material.CustomToolMaterial;
import org.spysat.simplehammers.util.AoeTool;

// This class represents a custom hammer item that extends the PickaxeItem class and implements the AoeTool interface.
public class ImpactHammerItem extends PickaxeItem implements AoeTool {

    // Declaration of hammer item instances for different materials.
    public static Item WOODEN_IMPACT_HAMMER;
    public static Item STONE_IMPACT_HAMMER;
    public static Item IRON_IMPACT_HAMMER;
    public static Item GOLD_IMPACT_HAMMER;
    public static Item DIAMOND_IMPACT_HAMMER;
    public static Item NETHERITE_IMPACT_HAMMER;

    // The radius of mining for this hammer.
    private final int miningRadius;

    // Constructor for HammerItem class.
    public ImpactHammerItem(String id) {
        // Calling the superclass constructor (PickaxeItem) with a custom tool material, mining speed, and attack damage.
        super(CustomToolMaterial.fromConfig(id), 1, -3.2F, new Settings());
        // Initializing the mining radius by getting the value from the configuration.
        this.miningRadius = ConfigProvider.CONFIG.getMiningRadius(id);
    }

    // A utility method to register a hammer item.
    private static Item registerImpactHammer(String id) {
        // Creating a new hammer item and registering it to the item registry.
        Item impactHammer = Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, id), new ImpactHammerItem(id));
        // Adding the newly created hammer to the tools item group.
        addToToolsGroup(impactHammer);
        return impactHammer;
    }

    // A utility method to add an item to the tools item group.
    private static void addToToolsGroup(Item item) {
        // Registering an event listener to modify entries in the tools item group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            // Adding the provided item to the group.
            entries.add(item);
        });
    }

    // A method to register all the different hammer items.
    public static void registerModItems() {
        // Registering hammer items for various materials.
        WOODEN_IMPACT_HAMMER = registerImpactHammer("wooden_impact_hammer");
        STONE_IMPACT_HAMMER = registerImpactHammer("stone_impact_hammer");
        IRON_IMPACT_HAMMER = registerImpactHammer("iron_impact_hammer");
        GOLD_IMPACT_HAMMER = registerImpactHammer("golden_impact_hammer");
        DIAMOND_IMPACT_HAMMER = registerImpactHammer("diamond_impact_hammer");
        NETHERITE_IMPACT_HAMMER = registerImpactHammer("netherite_impact_hammer");
    }

    // Overriding a method from the AoeTool interface to get the mining radius of the hammer.
    @Override
    public int getRadius(ItemStack stack) {
        return this.miningRadius;
    }

    // Overriding a method to determine whether to play break effects when mining.
    @Override
    public boolean playBreakEffects() {
        return false;
    }
}
