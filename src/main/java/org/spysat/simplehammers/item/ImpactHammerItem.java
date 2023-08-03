package org.spysat.simplehammers.item;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class ImpactHammerItem extends PickaxeItem {
    public ImpactHammerItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
