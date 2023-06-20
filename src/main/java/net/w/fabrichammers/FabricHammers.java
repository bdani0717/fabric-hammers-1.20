package net.w.fabrichammers;

import net.fabricmc.api.ModInitializer;
import net.w.fabrichammers.config.ConfigProvider;
import net.w.fabrichammers.item.HammerItem;
import net.w.fabrichammers.item.HammerItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricHammers implements ModInitializer {
	public static final String MOD_ID = "fabrichammers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading fabric hammers...");
		ConfigProvider.loadOrcreate();
		HammerItem.registerHammers();
		HammerItemGroup.registerItemGroup();
	}
}