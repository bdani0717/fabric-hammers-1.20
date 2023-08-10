package org.spysat.simplehammers.integration.jade;

import net.minecraft.block.Block;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.Identifiers;
import snownee.jade.api.WailaPlugin;
import snownee.jade.util.ClientProxy;

@WailaPlugin (SimpleHammersPlugin.ID)
public class SimpleHammersPlugin implements IWailaPlugin {
    public static final String ID = "simplehammers";
    public static IWailaClientRegistration CLIENT_REGISTRATION;

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        CLIENT_REGISTRATION = registration;

        registration.addConfig(Identifiers.MC_EFFECTIVE_TOOL, true);
        registration.registerBlockComponent(HarvestToolProvider.INSTANCE, Block.class);
        ClientProxy.registerReloadListener(HarvestToolProvider.INSTANCE);
    }
}
