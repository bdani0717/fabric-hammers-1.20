package org.spysat.simplehammers.config;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class Config {

    private static class HammerConfig {

        public static final TagKey<Block> MINEABLE_WITH_HAMMER = TagKey.of(RegistryKeys.BLOCK, new Identifier("simplehammers", "mineable_with_hammer")); //Custom block tag
        private String repairIngredient;
        private int enchantability;
        private int miningLevel;
        private int durability;
        private float attackDamage;
        private float attackSpeed;
        private ToolMaterial material;
        private TagKey<Block> effectiveBlocks;

        public HammerConfig() {
        }

        public HammerConfig(int durability, float attackSpeed, float attackDamage, int miningLevel, int enchantability, String repairIngredient) { // Hammer Config
            this.durability = durability;
            this.attackSpeed  = attackSpeed;
            this.attackDamage = attackDamage;
            this.miningLevel = miningLevel;
            this.enchantability = enchantability;
            this.repairIngredient = repairIngredient;
        }
    }

    private Map<String, HammerConfig> hammers = new HashMap<>() {{
        put("wooden_hammer", new HammerConfig(59, -3.3f, -2.1f, 1, 15, "minecraft:oak_planks"));
        put("stone_hammer", new HammerConfig(131, -3.3f, -2.1f, 1, 5, "minecraft:cobblestone"));
        put("iron_hammer",  new HammerConfig(250, -3.3f, -2.1f, 1, 14, "minecraft:iron_ingot"));
        put("gold_hammer",  new HammerConfig(100, -3.3f, -2.1f, 1, 22, "minecraft:gold_ingot"));
        put("diamond_hammer", new HammerConfig(1561, -3.3f, -2.1f, 1, 10, "minecraft:diamond"));
        put("netherite_hammer", new HammerConfig(2031, -3.3f, -2.1f, 1, 15, "minecraft:netherite_ingot"));
    }};

    public ToolMaterial getToolMaterial(String id) {
        return hammers.get(id).material;
    }


    private static class ImpactHammerConfig {
        private int durability;
        private int miningRadius;
        private float miningSpeedMultiplier;
        private float attackDamage;
        private int miningLevel;
        private int enchantability;
        private String repairIngredient;

        public ImpactHammerConfig() {}

        public ImpactHammerConfig(int durability, int miningRadius, float miningSpeedMultiplier, float attackDamage, int miningLevel, int enchantability, String repairIngredient) { //Impact Hammer Config
            this.durability = durability;
            this.miningRadius = miningRadius;
            this.miningSpeedMultiplier = miningSpeedMultiplier;
            this.attackDamage = attackDamage;
            this.miningLevel = miningLevel;
            this.enchantability = enchantability;
            this.repairIngredient = repairIngredient;
        }
    }

    private Map<String, ImpactHammerConfig> impacthammers = new HashMap<>() {{
        put("wooden_impact_hammer", new ImpactHammerConfig(59, 1, 0.57F, 6F, 0, 15, "minecraft:oak_planks"));
        put("stone_impact_hammer", new ImpactHammerConfig(131, 1, 1.15F, 8F, 1, 5, "minecraft:stone"));
        put("iron_impact_hammer", new ImpactHammerConfig(250, 1, 1.7F, 8F, 2, 14, "minecraft:iron_ingot"));
        put("golden_impact_hammer", new ImpactHammerConfig(100, 1, 3.5F, 6F, 0, 22, "minecraft:gold_ingot"));
        put("diamond_impact_hammer", new ImpactHammerConfig(1561, 1, 2.3F, 8F, 3, 10, "minecraft:diamond"));
        put("netherite_impact_hammer", new ImpactHammerConfig(2031, 1, 2.6F, 9F, 4, 15, "minecraft:netherite_ingot"));
    }};

    public int getDurability(String id) {
        return hammers.get(id).durability;
    }
    public float getAttackDamage(String id) {
        return hammers.get(id).attackDamage;
    }

    public float getAttackSpeed(String id) {
        return hammers.get(id).attackSpeed;
    }

    public int getMiningLevel(String id) {
        return hammers.get(id).miningLevel;
    }

    public int getEnchantability(String id) {
        return hammers.get(id).enchantability;
    }

    public String getRepairIngredient(String id) {
        return hammers.get(id).repairIngredient;
    }

    public int getImpactDurability(String id) {
        return impacthammers.get(id).durability;
    }
    public int getImpactMiningRadius(String id) { return impacthammers.get(id).miningRadius; }

    public float getImpactMiningSpeedMultiplier(String id) {
        return impacthammers.get(id).miningSpeedMultiplier;
    }
    public float getImpactAttackDamage(String id) {
        return impacthammers.get(id).attackDamage;
    }

    public int getImpactMiningLevel(String id) {
        return impacthammers.get(id).miningLevel;
    }

    public int getImpactEnchantability(String id) {
        return impacthammers.get(id).enchantability;
    }

    public String getImpactRepairIngredient(String id) {
        return impacthammers.get(id).repairIngredient;
    }

    private float impactHammerDurabilityModifier = 3.0F;

    public float getImpactHammerDurabilityModifier() { return this.impactHammerDurabilityModifier; }
}
