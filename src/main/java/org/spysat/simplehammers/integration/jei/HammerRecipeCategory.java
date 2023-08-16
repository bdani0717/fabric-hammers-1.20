package org.spysat.simplehammers.integration.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.common.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spysat.simplehammers.item.HammerItem;

import static org.spysat.simplehammers.SimpleHammers.MOD_ID;

public class HammerRecipeCategory implements IRecipeCategory<HammerRecipe> {

    private final IDrawable background;
    private final IDrawable icon;
    private final String leftSlotName = "inputBlock";
    private final String rightSlotName = "outputBlock";

    public static final @NotNull Identifier UID = new Identifier(MOD_ID, "hammer");

    public HammerRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper
                     .drawableBuilder(Constants.RECIPE_GUI_VANILLA, 0, 168, 125, 18)
                     .addPadding(0, 20,0,0)
                     .build();
        icon = guiHelper.createDrawableItemStack(new ItemStack(HammerItem.DIAMOND_HAMMER));
    }

    public @NotNull Identifier getUid() {
        return UID;
    }

    @Override
    public @NotNull RecipeType<HammerRecipe> getRecipeType() {
        return new RecipeType<>(UID, HammerRecipe.class);
    }

    @Override
    public @NotNull Text getTitle() {
        return Text.translatable("Hammering");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, HammerRecipe recipe, IFocusGroup focuses) {

    }
}
