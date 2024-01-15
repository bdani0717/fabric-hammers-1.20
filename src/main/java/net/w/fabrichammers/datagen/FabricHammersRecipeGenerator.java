package net.w.fabrichammers.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.w.fabrichammers.config.ConfigProvider;
import net.w.fabrichammers.item.EmeraldToolItem;
import net.w.fabrichammers.item.ExcavatorItem;
import net.w.fabrichammers.item.HammerItem;

public class FabricHammersRecipeGenerator extends FabricRecipeProvider {
	public FabricHammersRecipeGenerator(FabricDataOutput generator) {
		super(generator);
	}

	@Override
	public void generate(RecipeExporter exporter) {
		if (ConfigProvider.CONFIG.isEnableEmeraldTools()) return;

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EmeraldToolItem.EMERALD_PICKAXE)
				.pattern("EEE")
				.pattern(" S ")
				.pattern(" S ")
				.input('E', Items.EMERALD)
				.input('S', Items.STICK)
				.criterion(RecipeProvider.hasItem(Items.EMERALD), RecipeProvider.conditionsFromItem(Items.EMERALD))
				.criterion(RecipeProvider.hasItem(Items.STICK), RecipeProvider.conditionsFromItem(Items.STICK))
				.offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(EmeraldToolItem.EMERALD_PICKAXE)));

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EmeraldToolItem.EMERALD_SHOVEL)
				.pattern("E")
				.pattern("S")
				.pattern("S")
				.input('E', Items.EMERALD)
				.input('S', Items.STICK)
				.criterion(RecipeProvider.hasItem(Items.EMERALD), RecipeProvider.conditionsFromItem(Items.EMERALD))
				.criterion(RecipeProvider.hasItem(Items.STICK), RecipeProvider.conditionsFromItem(Items.STICK))
				.offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(EmeraldToolItem.EMERALD_SHOVEL)));

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EmeraldToolItem.EMERALD_SWORD)
				.pattern("E")
				.pattern("E")
				.pattern("S")
				.input('E', Items.EMERALD)
				.input('S', Items.STICK)
				.criterion(RecipeProvider.hasItem(Items.EMERALD), RecipeProvider.conditionsFromItem(Items.EMERALD))
				.criterion(RecipeProvider.hasItem(Items.STICK), RecipeProvider.conditionsFromItem(Items.STICK))
				.offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(EmeraldToolItem.EMERALD_SWORD)));

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EmeraldToolItem.EMERALD_AXE)
				.pattern("EE")
				.pattern("ES")
				.pattern(" S")
				.input('E', Items.EMERALD)
				.input('S', Items.STICK)
				.criterion(RecipeProvider.hasItem(Items.EMERALD), RecipeProvider.conditionsFromItem(Items.EMERALD))
				.criterion(RecipeProvider.hasItem(Items.STICK), RecipeProvider.conditionsFromItem(Items.STICK))
				.offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(EmeraldToolItem.EMERALD_AXE)));

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, EmeraldToolItem.EMERALD_HOE)
				.pattern("EE")
				.pattern(" S")
				.pattern(" S")
				.input('E', Items.EMERALD)
				.input('S', Items.STICK)
				.criterion(RecipeProvider.hasItem(Items.EMERALD), RecipeProvider.conditionsFromItem(Items.EMERALD))
				.criterion(RecipeProvider.hasItem(Items.STICK), RecipeProvider.conditionsFromItem(Items.STICK))
				.offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(EmeraldToolItem.EMERALD_HOE)));

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, HammerItem.EMERALD_HAMMER)
				.pattern("BEB")
				.pattern(" S ")
				.pattern(" S ")
				.input('E', Items.EMERALD)
				.input('B', Items.EMERALD_BLOCK)
				.input('S', Items.STICK)
				.criterion(RecipeProvider.hasItem(Items.EMERALD), RecipeProvider.conditionsFromItem(Items.EMERALD))
				.criterion(RecipeProvider.hasItem(Items.STICK), RecipeProvider.conditionsFromItem(Items.STICK))
				.offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(HammerItem.EMERALD_HAMMER)));

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ExcavatorItem.EMERALD_EXCAVATOR)
				.pattern("EBE")
				.pattern("ESE")
				.pattern(" S ")
				.input('E', Items.EMERALD)
				.input('B', Items.EMERALD_BLOCK)
				.input('S', Items.STICK)
				.criterion(RecipeProvider.hasItem(Items.EMERALD), RecipeProvider.conditionsFromItem(Items.EMERALD))
				.criterion(RecipeProvider.hasItem(Items.STICK), RecipeProvider.conditionsFromItem(Items.STICK))
				.offerTo(exporter, new Identifier(RecipeProvider.getRecipeName(ExcavatorItem.EMERALD_EXCAVATOR)));
	}
}