package net.w.fabrichammers.config;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class Config {
    private class HammerConfig {
        private int durability;
        private int miningRadius;
        private float miningSpeedMultiplier;
        private float attackDamage;
        private int miningLevel;
        private int enchantability;
        private String repairIngredient;

        public HammerConfig() {}

        public HammerConfig(int durability, int miningRadius, float miningSpeedMultiplier, float attackDamage, int miningLevel, int enchantability, String repairIngredient) {
            this.durability = durability;
            this.miningRadius = miningRadius;
            this.miningSpeedMultiplier = miningSpeedMultiplier;
            this.attackDamage = attackDamage;
            this.miningLevel = miningLevel;
            this.enchantability = enchantability;
            this.repairIngredient = repairIngredient;
        }
    }

    private String configVersion = "1.3";

    private Map<String, HammerConfig> tools = new HashMap<>() {{
            put("wooden_hammer", new HammerConfig(59, 1, 0.57F, 6F, 0, 15, "minecraft:oak_planks"));
            put("stone_hammer", new HammerConfig(131, 1, 1.15F, 8F, 1, 5, "minecraft:stone"));
            put("iron_hammer", new HammerConfig(250, 1, 1.7F, 8F, 2, 14, "minecraft:iron_ingot"));
            put("golden_hammer", new HammerConfig(100, 1, 3.5F, 6F, 0, 22, "minecraft:gold_ingot"));
            put("emerald_hammer", new HammerConfig(750, 1, 2F, 8F, 2, 10, "minecraft:emerald"));
            put("diamond_hammer", new HammerConfig(1561, 1, 2.3F, 8F, 3, 10, "minecraft:diamond"));
            put("netherite_hammer", new HammerConfig(2031, 1, 2.6F, 9F, 4, 15, "minecraft:netherite_ingot"));

            put("wooden_excavator", new HammerConfig(59, 1, 0.57F, 2F, 0, 15, "minecraft:oak_planks"));
            put("stone_excavator", new HammerConfig(131, 1, 1.15F, 6F, 1, 5, "minecraft:stone"));
            put("iron_excavator", new HammerConfig(250, 1, 1.7F, 6F, 2, 14, "minecraft:iron_ingot"));
            put("golden_excavator", new HammerConfig(100, 1, 3.5F, 4F, 0, 22, "minecraft:gold_ingot"));
            put("emerald_excavator", new HammerConfig(750, 1, 2F, 6F, 2, 10, "minecraft:emerald"));
            put("diamond_excavator", new HammerConfig(1561, 1, 2.3F, 6F, 3, 10, "minecraft:diamond"));
            put("netherite_excavator", new HammerConfig(2031, 1, 2.6F, 7F, 4, 15, "minecraft:netherite_ingot"));
    }};

    private float hammerDurabilityMultipier = 3.0F;
    private boolean enableEmeradlTools = true;



    public int getDurability(String id) { return tools.get(id).durability; }

    public int getMiningRadius(String id) { return tools.get(id).miningRadius; }

    public float getMiningSpeedMultiplier(String id) {
        return tools.get(id).miningSpeedMultiplier;
    }

    public float getAttackDamage(String id) {
        return tools.get(id).attackDamage;
    }

    public int getMiningLevel(String id) {
        return tools.get(id).miningLevel;
    }

    public int getEnchantability(String id) {
        return tools.get(id).enchantability;
    }

    public String getRepairIngredient(String id) {
        return tools.get(id).repairIngredient;
    }

    public float getHammerDurabilityMultipier() { return this.hammerDurabilityMultipier; }

    public boolean isEnableEmeradlTools() { return this.enableEmeradlTools; }

    public String getConfigVersion() { return this.configVersion; }
}
