package net.w.fabrichammers.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.w.fabrichammers.FabricHammers;
import net.w.fabrichammers.config.ConfigProvider;

public class HammerMaterial implements ToolMaterial {
    private int durability;
    private float miningSpeedMultiplier;
    private float attackDamage;
    private int miningLevel;
    private int enchantability;
    private Ingredient repairIngredient;

    public HammerMaterial(
            int durability,
            float miningSpeedMultiplier,
            float attackDamage,
            int miningLevel,
            int enchantability,
            Ingredient repairIngredient
    ) {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.miningLevel = miningLevel;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    public static HammerMaterial fromConfig(String id) {
        return new HammerMaterial(
                (int)(ConfigProvider.CONFIG.getDurability(id) * ConfigProvider.CONFIG.getDurabilityMultipier()),
                ConfigProvider.CONFIG.getMiningSpeedMultiplier(id),
                ConfigProvider.CONFIG.getAttackDamage(id),
                ConfigProvider.CONFIG.getMiningLevel(id),
                ConfigProvider.CONFIG.getEnchantability(id),
                Ingredient.ofItems(Registries.ITEM.get(new Identifier(ConfigProvider.CONFIG.getRepairIngredient(id))))
        );
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}
