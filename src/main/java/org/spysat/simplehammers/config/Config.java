package org.spysat.simplehammers.config;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class Config {
    private class HammerConfig {
        private class HammerRecipe {
            String input;
            String output;

            public HammerRecipe(String inputBlock, String outputBlock) {
                this.input = inputBlock;
                this.output = outputBlock;
            }

            public HammerRecipe() {}
        }

        private ArrayList<HammerRecipe> recipeList = new ArrayList<>() {{
            recipeList.add(new HammerRecipe("minecraft:tuff", "minecraft:basalt"));
            recipeList.add(new HammerRecipe("minecraft:granite", "minecraft:dripstone_block"));
            recipeList.add(new HammerRecipe("minecraft:smooth_stone", "minecraft:stone"));
            recipeList.add(new HammerRecipe("minecraft:stone", "minecraft:cobblestone"));
            recipeList.add(new HammerRecipe("minecraft:cobblestone", "minecraft:gravel"));
            recipeList.add(new HammerRecipe("minecraft:gravel", "minecraft:dirt"));
            recipeList.add(new HammerRecipe("minecraft:dirt", "minecraft:sand"));
            recipeList.add(new HammerRecipe("minecraft:sand", "simplehammers:dust"));
        }};

        public HammerConfig(ArrayList<HammerRecipe> recipeList) {
            this.recipeList = recipeList;
        }

        public HammerConfig() {}

        public @Nullable HammerRecipe getRecipeAtIndex(int i) {
            if (i >= 0 && i < recipeList.size()) {
                return recipeList.get(i);
            }
            return null;
        }
    }

    private class ImpactHammerConfig {
        private int durability;
        private int miningRadius;
        private float miningSpeedMultiplier;
        private float attackDamage;
        private int miningLevel;
        private int enchantability;
        private String repairIngredient;

        public ImpactHammerConfig() {
        }

        public ImpactHammerConfig(int durability, int miningRadius, float miningSpeedMultiplier, float attackDamage, int miningLevel, int enchantability, String repairIngredient, boolean isFireproof) {
            this.durability = durability;
            this.miningRadius = miningRadius;
            this.miningSpeedMultiplier = miningSpeedMultiplier;
            this.attackDamage = attackDamage;
            this.miningLevel = miningLevel;
            this.enchantability = enchantability;
            this.repairIngredient = repairIngredient;
        }
    }

    private Map<String, ImpactHammerConfig> impactHammers = new HashMap<>() {{
        put("stone_impact_hammer",    new ImpactHammerConfig(131, 1, 1.15F, 8F, 1, 5,  "minecraft:cobblestone", false));
        put("iron_impact_hammer",     new ImpactHammerConfig(250, 1, 1.7F, 8F, 2, 14, "minecraft:iron_ingot", false));
        put("golden_impact_hammer",   new ImpactHammerConfig(100, 1, 3.5F, 6F, 0, 22, "minecraft:gold_ingot", false));
        put("diamond_impact_hammer",  new ImpactHammerConfig(1561, 2, 2.3F, 8F, 3, 10,  "minecraft:diamond", false));
        put("netherite_impact_hammer",new ImpactHammerConfig(2031, 2, 2.6F, 9F, 4, 15,"minecraft:netherite_ingot", true));
    }};

    public int getDurability(String id) { return impactHammers.get(id).durability; }

    public int getMiningRadius(String id) { return impactHammers.get(id).miningRadius; }

    public float getMiningSpeedMultiplier(String id) { return impactHammers.get(id).miningSpeedMultiplier; }

    public float getAttackDamage(String id) { return impactHammers.get(id).attackDamage; }

    public int getMiningLevel(String id) { return impactHammers.get(id).miningLevel; }

    public int getEnchantability(String id) { return impactHammers.get(id).enchantability; }

    public String getRepairIngredient(String id) { return impactHammers.get(id).repairIngredient; }
}