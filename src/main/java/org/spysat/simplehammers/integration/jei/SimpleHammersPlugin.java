package org.spysat.simplehammers.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spysat.simplehammers.SimpleHammers;
import org.spysat.simplehammers.item.ModItemGroup;

import java.util.HashSet;

public class SimpleHammersPlugin implements IModPlugin {
    public static final @NotNull Identifier SIMPLEHAMMERS_JEI = new @NotNull Identifier(SimpleHammers.MOD_ID, "jei");

    /**
     * The unique ID for this mod plugin.
     * The namespace should be your mod's modId.
     */
    @Override
    public @NotNull Identifier getPluginUid() {
        return SIMPLEHAMMERS_JEI;
    }

    /**
     * Register the categories handled by this plugin.
     * These are registered before recipes so they can be checked for validity.
     *
     * @param registration
     */
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IModPlugin.super.registerCategories(registration);
    }

    /**
     * Register modded recipes.
     *
     * @param registration
     */
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IModPlugin.super.registerRecipes(registration);
    }

    /**
     * Register recipe catalysts.
     * Recipe Catalysts are ingredients that are needed in order to craft other things.
     * Vanilla examples of Recipe Catalysts are the Crafting Table and Furnace.
     *
     * @param registration
     */
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
    }

    /**
     * Register various GUI-related things for your mod.
     * This includes adding clickable areas in your guis to open JEI,
     * and adding areas on the screen that JEI should avoid drawing.
     *
     * @param registration
     */
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        IModPlugin.super.registerGuiHandlers(registration);
    }
}