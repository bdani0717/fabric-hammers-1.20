package org.spysat.simplehammers.config;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

import static org.spysat.simplehammers.item.HammerItem.MINEABLE_WITH_HAMMER;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class Config {
    private static class HammerConfig {

        public static final TagKey<Block> MINEABLE_WITH_HAMMER = TagKey.of(RegistryKeys.BLOCK, new Identifier("simplehammers", "hammerables")); //Custom block tag
        private int durability;
        private int miningRadius;
        private float miningSpeedMultiplier;
        private float attackDamage;
        private ToolMaterial material;
        private TagKey<Block> effectiveBlocks;
        private int miningLevel;
        private int enchantability;
        private String repairIngredient;

        public HammerConfig() {}

        public HammerConfig(int durability, int miningRadius, float miningSpeedMultiplier, float attackDamage, int miningLevel, int enchantability, String repairIngredient) { //Impact Hammer Config
            this.durability = durability;
            this.miningRadius = miningRadius;
            this.miningSpeedMultiplier = miningSpeedMultiplier;
            this.attackDamage = attackDamage;
            this.miningLevel = miningLevel;
            this.enchantability = enchantability;
            this.repairIngredient = repairIngredient;
        }

        public HammerConfig(float attackDamage, float attackSpeed, ToolMaterials material) { // Hammer Config
            this.attackDamage = attackDamage;
            this.miningSpeedMultiplier = attackSpeed;
            this.material = material;
        }

    }
    private Map<String, HammerConfig> hammers = new HashMap<>() {{
        put("wooden_hammer", new HammerConfig(1.2f, -3.3f, ToolMaterials.WOOD));
        put("stone_hammer", new HammerConfig(1.3f, -3.3f, ToolMaterials.STONE));
        put("iron_hammer", new HammerConfig(1.5f, -3.2f, ToolMaterials.IRON));
        put("gold_hammer", new HammerConfig(1.2f, -3.0f, ToolMaterials.GOLD));
        put("diamond_hammer", new HammerConfig(1.5f, -3.1f, ToolMaterials.DIAMOND));
        put("netherite_hammer", new HammerConfig(1.6f, -3.1f, ToolMaterials.NETHERITE));
    }};

    private Map<String, HammerConfig> impacthammers = new HashMap<>() {{
        put("wooden_impact_hammer", new HammerConfig(59, 1, 0.57F, 6F, 0, 15, "minecraft:log"));
        put("stone_impact_hammer", new HammerConfig(131, 1, 1.15F, 8F, 1, 5, "minecraft:stone"));
        put("iron_impact_hammer", new HammerConfig(250, 1, 1.7F, 8F, 2, 14, "minecraft:iron_ingot"));
        put("golden_impact_hammer", new HammerConfig(100, 1, 3.5F, 6F, 0, 22, "minecraft:gold_ingot"));
        put("diamond_impact_hammer", new HammerConfig(1561, 1, 2.3F, 8F, 3, 10, "minecraft:diamond"));
        put("netherite_impact_hammer", new HammerConfig(2031, 1, 2.6F, 9F, 4, 15, "minecraft:netherite_ingot"));
    }};


    public ToolMaterial getToolMaterial(String id) {
        return hammers.get(id).material;
    }
    private float impactHammerDurabilityModifier = 3.0F;

    public int getDurability(String id) {
        return impacthammers.get(id).durability;
    }
    public int getMiningRadius(String id) { return impacthammers.get(id).miningRadius; }

    public float getMiningSpeedMultiplier(String id) {
        return impacthammers.get(id).miningSpeedMultiplier;
    }
    public float getAttackDamage(String id) {
        return impacthammers.get(id).attackDamage;
    }

    public int getMiningLevel(String id) {
        return impacthammers.get(id).miningLevel;
    }

    public int getEnchantability(String id) {
        return impacthammers.get(id).enchantability;
    }

    public String getRepairIngredient(String id) {
        return impacthammers.get(id).repairIngredient;
    }

    public float getHammerDurabilityModifier() { return this.impactHammerDurabilityModifier; }

}
