package org.spysat.simplehammers.material;
import net.minecraft.item.ToolMaterial;

import org.spysat.simplehammers.config.ConfigProvider;

public class VanillaToolMaterial {
    public static ToolMaterial fromConfig(String id) {
        return (ConfigProvider.CONFIG.getToolMaterial(id));
    }
}
