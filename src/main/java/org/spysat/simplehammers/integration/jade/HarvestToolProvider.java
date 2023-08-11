package org.spysat.simplehammers.integration.jade;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spysat.simplehammers.item.HammerItem;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec2f;
import snownee.jade.addon.harvest.SimpleToolHandler;
import snownee.jade.addon.harvest.ToolHandler;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.impl.ui.SubTextElement;
import snownee.jade.util.CommonProxy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.spysat.simplehammers.item.HammerItem.*;

//TODO: Fix the bug where Hammer icons don't show up properly
public enum HarvestToolProvider implements IBlockComponentProvider, SynchronousResourceReloader {
    INSTANCE;

    public static final Cache<BlockState, ImmutableList<ItemStack>> resultCache = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();
    public static final Map<String, ToolHandler> TOOL_HANDLERS = Maps.newLinkedHashMap();
    private static final Text CHECK = Text.literal("✔");
    private static final Text X = Text.literal("✕");
    private static final Vec2f ITEM_SIZE = new Vec2f(10, 0);

    static {
        if (CommonProxy.isPhysicallyClient()) {
            registerHandler(new SimpleToolHandler("hammer", HAMMERABLES, HammerItem.WOODEN_HAMMER, HammerItem.STONE_HAMMER, HammerItem.IRON_HAMMER, HammerItem.GOLD_HAMMER, HammerItem.DIAMOND_HAMMER, HammerItem.NETHERITE_HAMMER));
        }
    }

    public static ImmutableList<ItemStack> getTool(BlockState state, World world, BlockPos pos) {
        ImmutableList.Builder<ItemStack> tools = ImmutableList.builder();
        for (ToolHandler handler : TOOL_HANDLERS.values()) {
            ItemStack tool = handler.test(state, world, pos);
            if (!tool.isEmpty()) {
                tools.add(tool);
            }
        }
        return tools.build();
    }

    public static synchronized void registerHandler(ToolHandler handler) {
        TOOL_HANDLERS.put(handler.getName(), handler);
    }

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        PlayerEntity player = accessor.getPlayer();
        if (player.isCreative() || player.isSpectator()) {
            return;
        }
        BlockState state = accessor.getBlockState();
        float hardness = state.getHardness(accessor.getLevel(), accessor.getPosition());
        if (hardness < 0) {
            if (config.get(Identifiers.MC_SHOW_UNBREAKABLE)) {
                Text text = IThemeHelper.get().failure(Text.translatable("jade.harvest_tool.unbreakable"));
                tooltip.add(IElementHelper.get().text(text).message(null));
            }
            return;
        }

        boolean newLine = config.get(Identifiers.MC_HARVEST_TOOL_NEW_LINE);
        List<IElement> elements = getText(accessor, config);
        if (elements.isEmpty()) {
            return;
        }
        elements.forEach(e -> e.message(null));
        if (newLine) {
            tooltip.add(elements);
        } else {
            elements.forEach(e -> e.align(IElement.Align.RIGHT));
            tooltip.append(0, elements);
        }
    }

    /**
     * Generates a list of tooltip elements to provide information about the tools required
     * to interact with a specific block, based on the block's properties and configuration settings.
     *
     * @param accessor The BlockAccessor providing access to information about the block.
     * @param config   The IPluginConfig containing configuration options for tooltip generation.
     * @return A list of IElement objects representing tooltip elements, or an empty list if no
     *         relevant tools or information were found for the block.
     */
    public List<IElement> getText(BlockAccessor accessor, IPluginConfig config) {
        BlockState state = accessor.getBlockState();

        // Check if the block requires a tool and if the effective tool configuration is enabled
        if (!state.isToolRequired() && !config.get(Identifiers.MC_EFFECTIVE_TOOL)) {
            return List.of(); // Return an empty list if no relevant information is needed
        }
        //Why do these tooltips not show up?
        List<ItemStack> tools = List.of(new ItemStack(WOODEN_HAMMER), new ItemStack(STONE_HAMMER), new ItemStack(IRON_HAMMER), new ItemStack(GOLD_HAMMER), new ItemStack(DIAMOND_HAMMER), new ItemStack(NETHERITE_HAMMER)); // Initialize the list of tools
//
//        try {
//            // Attempt to fetch tools from the cache or compute and cache them
//            tools = resultCache.get(state, () -> getTool(state, accessor.getLevel(), accessor.getPosition()));
//        } catch (ExecutionException e) {
//            e.printStackTrace(); // Print the exception stack trace in case of an error
//        }
//
//        // If no suitable tools were found, return an empty list
//        if (tools.isEmpty()) {
//            return List.of();
//        }

        int offsetY = 0;

        // Check if new line configuration is enabled, adjust offsetY if not
        if (!config.get(Identifiers.MC_HARVEST_TOOL_NEW_LINE)) {
            offsetY = -3;
        }

        List<IElement> elements = Lists.newArrayList();

        // Create tooltip elements for each tool
        for (ItemStack tool : tools) {
            elements.add(IElementHelper.get().item(tool, 0.75f)
                    .translate(new Vec2f(-1, offsetY))
                    .size(ITEM_SIZE)
                    .message(null));
        }

        // Add a spacer element for visual separation
        if (!elements.isEmpty()) {
            elements.add(0, IElementHelper.get().spacer(5, 0));

            // Check if the held item can harvest the block or is a suitable tool
            ItemStack held = accessor.getPlayer().getActiveItem();
            boolean canHarvest = held.isSuitableFor(state);
            if (CommonProxy.isShearable(state) && CommonProxy.isShears(held)) {
                canHarvest = true;
            }

            // Add a success or danger icon based on harvesting capability
            if (state.isToolRequired() || canHarvest) {
                IThemeHelper t = IThemeHelper.get();
                Text text = canHarvest ? t.success(CHECK) : t.danger(X);
                elements.add(new SubTextElement(text).translate(new Vec2f(-3, 7 + offsetY)));
            }
        }

        return elements; // Return the generated tooltip elements
    }

    @Override
    public Identifier getUid() {
        return Identifiers.MC_HARVEST_TOOL;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD + 2500;
    }

    @Override
    public void reload(ResourceManager manager) {
        resultCache.invalidateAll();
    }
}
