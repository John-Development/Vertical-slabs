package net.verticalslabs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.verticalslabs.block.VerticalSlabBlocks.*;

public class VerticalSlabs implements ModInitializer {
  public static final String MOD_ID = "verticalslabs";

  @Override
  public void onInitialize() {
    registerBlockItem("vertical_oak_slab", VERTICAL_OAK_SLAB);
    registerBlockItem("vertical_spruce_slab", VERTICAL_SPRUCE_SLAB);
    registerBlockItem("vertical_birch_slab", VERTICAL_BIRCH_SLAB);
    registerBlockItem("vertical_jungle_slab", VERTICAL_JUNGLE_SLAB);
    registerBlockItem("vertical_acacia_slab", VERTICAL_ACACIA_SLAB);
    registerBlockItem("vertical_dark_oak_slab", VERTICAL_DARK_OAK_SLAB);
    registerBlockItem("vertical_mangrove_slab", VERTICAL_MANGROVE_SLAB);
    registerBlockItem("vertical_cherry_slab", VERTICAL_CHERRY_SLAB);
    registerBlockItem("vertical_bamboo_slab", VERTICAL_BAMBOO_SLAB);
    registerBlockItem("vertical_bamboo_mosaic_slab", VERTICAL_BAMBOO_MOSAIC_SLAB);
    registerBlockItem("vertical_crimson_slab", VERTICAL_CRIMSON_SLAB);
    registerBlockItem("vertical_warped_slab", VERTICAL_WARPED_SLAB);

    registerBlockItem("vertical_stone_slab", VERTICAL_STONE_SLAB);
    registerBlockItem("vertical_smooth_stone_slab", VERTICAL_SMOOTH_STONE_SLAB);
    registerBlockItem("vertical_sandstone_slab", VERTICAL_SANDSTONE_SLAB);
    registerBlockItem("vertical_cut_sandstone_slab", VERTICAL_CUT_SANDSTONE_SLAB);
    registerBlockItem("vertical_cobblestone_slab", VERTICAL_COBBLESTONE_SLAB);
    registerBlockItem("vertical_brick_slab", VERTICAL_BRICK_SLAB);
    registerBlockItem("vertical_stone_brick_slab", VERTICAL_STONE_BRICK_SLAB);
    registerBlockItem("vertical_nether_brick_slab", VERTICAL_NETHER_BRICK_SLAB);
    registerBlockItem("vertical_quartz_slab", VERTICAL_QUARTZ_SLAB);
    registerBlockItem("vertical_red_sandstone_slab", VERTICAL_RED_SANDSTONE_SLAB);
    registerBlockItem("vertical_cut_red_sandstone_slab", VERTICAL_CUT_RED_SANDSTONE_SLAB);
    registerBlockItem("vertical_purpur_slab", VERTICAL_PURPUR_SLAB);
    registerBlockItem("vertical_prismarine_slab", VERTICAL_PRISMARINE_SLAB);
    registerBlockItem("vertical_prismarine_brick_slab", VERTICAL_PRISMARINE_BRICK_SLAB);
    registerBlockItem("vertical_dark_prismarine_slab", VERTICAL_DARK_PRISMARINE_SLAB);
    registerBlockItem("vertical_polished_granite_slab", VERTICAL_POLISHED_GRANITE_SLAB);
    registerBlockItem("vertical_smooth_red_sandstone_slab", VERTICAL_SMOOTH_RED_SANDSTONE_SLAB);
    registerBlockItem("vertical_mossy_stone_brick_slab", VERTICAL_MOSSY_STONE_BRICK_SLAB);
    registerBlockItem("vertical_polished_diorite_slab", VERTICAL_POLISHED_DIORITE_SLAB);
    registerBlockItem("vertical_mossy_cobblestone_slab", VERTICAL_MOSSY_COBBLESTONE_SLAB);
    registerBlockItem("vertical_end_stone_brick_slab", VERTICAL_END_STONE_BRICK_SLAB);
    registerBlockItem("vertical_smooth_sandstone_slab", VERTICAL_SMOOTH_SANDSTONE_SLAB);
    registerBlockItem("vertical_smooth_quartz_slab", VERTICAL_SMOOTH_QUARTZ_SLAB);
    registerBlockItem("vertical_granite_slab", VERTICAL_GRANITE_SLAB);
    registerBlockItem("vertical_andesite_slab", VERTICAL_ANDESITE_SLAB);
    registerBlockItem("vertical_red_nether_brick_slab", VERTICAL_RED_NETHER_BRICK_SLAB);
    registerBlockItem("vertical_polished_andesite_slab", VERTICAL_POLISHED_ANDESITE_SLAB);
    registerBlockItem("vertical_diorite_slab", VERTICAL_DIORITE_SLAB);
    registerBlockItem("vertical_blackstone_slab", VERTICAL_BLACKSTONE_SLAB);
    registerBlockItem("vertical_polished_blackstone_slab", VERTICAL_POLISHED_BLACKSTONE_SLAB);
    registerBlockItem("vertical_polished_blackstone_brick_slab", VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB);
    registerBlockItem("vertical_cobbled_deepslate_slab", VERTICAL_COBBLED_DEEPSLATE_SLAB);
    registerBlockItem("vertical_polished_deepslate_slab", VERTICAL_POLISHED_DEEPSLATE_SLAB);
    registerBlockItem("vertical_deepslate_brick_slab", VERTICAL_DEEPSLATE_BRICK_SLAB);
    registerBlockItem("vertical_deepslate_tile_slab", VERTICAL_DEEPSLATE_TILE_SLAB);
    registerBlockItem("vertical_mud_brick_slab", VERTICAL_MUD_BRICK_SLAB);

    registerBlockItem("vertical_cut_copper_slab", VERTICAL_CUT_COPPER_SLAB);
    registerBlockItem("vertical_exposed_cut_copper_slab", VERTICAL_EXPOSED_CUT_COPPER_SLAB);
    registerBlockItem("vertical_weathered_cut_copper_slab", VERTICAL_WEATHERED_CUT_COPPER_SLAB);
    registerBlockItem("vertical_oxidized_cut_copper_slab", VERTICAL_OXIDIZED_CUT_COPPER_SLAB);
    registerBlockItem("vertical_waxed_cut_copper_slab", VERTICAL_WAXED_CUT_COPPER_SLAB);
    registerBlockItem("vertical_waxed_exposed_cut_copper_slab", VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB);
    registerBlockItem("vertical_waxed_weathered_cut_copper_slab", VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB);
    registerBlockItem("vertical_waxed_oxidized_cut_copper_slab", VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB);

    initOxidizableChains();
    initCreativePlacement();
  }

  private void registerBlockItem(String path, Block block) {
    Registry.register(Registries.BLOCK, new Identifier(MOD_ID, path), block);
    Registry.register(Registries.ITEM, new Identifier(MOD_ID, path), new BlockItem(block, new Item.Settings()));
  }

  private void initOxidizableChains() {
    OxidizableBlocksRegistry.registerOxidizableBlockPair(VERTICAL_CUT_COPPER_SLAB, VERTICAL_EXPOSED_CUT_COPPER_SLAB);
    OxidizableBlocksRegistry.registerOxidizableBlockPair(VERTICAL_EXPOSED_CUT_COPPER_SLAB, VERTICAL_WEATHERED_CUT_COPPER_SLAB);
    OxidizableBlocksRegistry.registerOxidizableBlockPair(VERTICAL_WEATHERED_CUT_COPPER_SLAB, VERTICAL_OXIDIZED_CUT_COPPER_SLAB);
    OxidizableBlocksRegistry.registerWaxableBlockPair(VERTICAL_CUT_COPPER_SLAB, VERTICAL_WAXED_CUT_COPPER_SLAB);
    OxidizableBlocksRegistry.registerWaxableBlockPair(VERTICAL_EXPOSED_CUT_COPPER_SLAB, VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB);
    OxidizableBlocksRegistry.registerWaxableBlockPair(VERTICAL_WEATHERED_CUT_COPPER_SLAB, VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB);
    OxidizableBlocksRegistry.registerWaxableBlockPair(VERTICAL_OXIDIZED_CUT_COPPER_SLAB, VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB);
  }

  private void initCreativePlacement() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
      content.addBefore(Items.OAK_SLAB, VERTICAL_OAK_SLAB);
      content.addBefore(Items.SPRUCE_SLAB, VERTICAL_SPRUCE_SLAB);
      content.addBefore(Items.BIRCH_SLAB, VERTICAL_BIRCH_SLAB);
      content.addBefore(Items.JUNGLE_SLAB, VERTICAL_JUNGLE_SLAB);
      content.addBefore(Items.ACACIA_SLAB, VERTICAL_ACACIA_SLAB);
      content.addBefore(Items.DARK_OAK_SLAB, VERTICAL_DARK_OAK_SLAB);
      content.addBefore(Items.CRIMSON_SLAB, VERTICAL_CRIMSON_SLAB);
      content.addBefore(Items.WARPED_SLAB, VERTICAL_WARPED_SLAB);
      content.addBefore(Items.STONE_SLAB, VERTICAL_STONE_SLAB);
      content.addBefore(Items.SMOOTH_STONE_SLAB, VERTICAL_SMOOTH_STONE_SLAB);
      content.addBefore(Items.SANDSTONE_SLAB, VERTICAL_SANDSTONE_SLAB);
      content.addBefore(Items.CUT_SANDSTONE_SLAB, VERTICAL_CUT_SANDSTONE_SLAB);
      content.addBefore(Items.COBBLESTONE_SLAB, VERTICAL_COBBLESTONE_SLAB);
      content.addBefore(Items.BRICK_SLAB, VERTICAL_BRICK_SLAB);
      content.addBefore(Items.STONE_BRICK_SLAB, VERTICAL_STONE_BRICK_SLAB);
      content.addBefore(Items.NETHER_BRICK_SLAB, VERTICAL_NETHER_BRICK_SLAB);
      content.addBefore(Items.QUARTZ_SLAB, VERTICAL_QUARTZ_SLAB);
      content.addBefore(Items.RED_SANDSTONE_SLAB, VERTICAL_RED_SANDSTONE_SLAB);
      content.addBefore(Items.CUT_RED_SANDSTONE_SLAB, VERTICAL_CUT_RED_SANDSTONE_SLAB);
      content.addBefore(Items.PURPUR_SLAB, VERTICAL_PURPUR_SLAB);
      content.addBefore(Items.PRISMARINE_SLAB, VERTICAL_PRISMARINE_SLAB);
      content.addBefore(Items.PRISMARINE_BRICK_SLAB, VERTICAL_PRISMARINE_BRICK_SLAB);
      content.addBefore(Items.DARK_PRISMARINE_SLAB, VERTICAL_DARK_PRISMARINE_SLAB);
      content.addBefore(Items.POLISHED_GRANITE_SLAB, VERTICAL_POLISHED_GRANITE_SLAB);
      content.addBefore(Items.SMOOTH_RED_SANDSTONE_SLAB, VERTICAL_SMOOTH_RED_SANDSTONE_SLAB);
      content.addBefore(Items.MOSSY_STONE_BRICK_SLAB, VERTICAL_MOSSY_STONE_BRICK_SLAB);
      content.addBefore(Items.POLISHED_DIORITE_SLAB, VERTICAL_POLISHED_DIORITE_SLAB);
      content.addBefore(Items.MOSSY_COBBLESTONE_SLAB, VERTICAL_MOSSY_COBBLESTONE_SLAB);
      content.addBefore(Items.END_STONE_BRICK_SLAB, VERTICAL_END_STONE_BRICK_SLAB);
      content.addBefore(Items.SMOOTH_SANDSTONE_SLAB, VERTICAL_SMOOTH_SANDSTONE_SLAB);
      content.addBefore(Items.SMOOTH_QUARTZ_SLAB, VERTICAL_SMOOTH_QUARTZ_SLAB);
      content.addBefore(Items.GRANITE_SLAB, VERTICAL_GRANITE_SLAB);
      content.addBefore(Items.ANDESITE_SLAB, VERTICAL_ANDESITE_SLAB);
      content.addBefore(Items.RED_NETHER_BRICK_SLAB, VERTICAL_RED_NETHER_BRICK_SLAB);
      content.addBefore(Items.POLISHED_ANDESITE_SLAB, VERTICAL_POLISHED_ANDESITE_SLAB);
      content.addBefore(Items.DIORITE_SLAB, VERTICAL_DIORITE_SLAB);
      content.addBefore(Items.BLACKSTONE_SLAB, VERTICAL_BLACKSTONE_SLAB);
      content.addBefore(Items.POLISHED_BLACKSTONE_SLAB, VERTICAL_POLISHED_BLACKSTONE_SLAB);
      content.addBefore(Items.POLISHED_BLACKSTONE_BRICK_SLAB, VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB);
      content.addBefore(Items.COBBLED_DEEPSLATE_SLAB, VERTICAL_COBBLED_DEEPSLATE_SLAB);
      content.addBefore(Items.POLISHED_DEEPSLATE_SLAB, VERTICAL_POLISHED_DEEPSLATE_SLAB);
      content.addBefore(Items.DEEPSLATE_BRICK_SLAB, VERTICAL_DEEPSLATE_BRICK_SLAB);
      content.addBefore(Items.DEEPSLATE_TILE_SLAB, VERTICAL_DEEPSLATE_TILE_SLAB);
      content.addBefore(Items.CUT_COPPER_SLAB, VERTICAL_CUT_COPPER_SLAB);
      content.addBefore(Items.EXPOSED_CUT_COPPER_SLAB, VERTICAL_EXPOSED_CUT_COPPER_SLAB);
      content.addBefore(Items.WEATHERED_CUT_COPPER_SLAB, VERTICAL_WEATHERED_CUT_COPPER_SLAB);
      content.addBefore(Items.OXIDIZED_CUT_COPPER_SLAB, VERTICAL_OXIDIZED_CUT_COPPER_SLAB);
      content.addBefore(Items.WAXED_CUT_COPPER_SLAB, VERTICAL_WAXED_CUT_COPPER_SLAB);
      content.addBefore(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB);
      content.addBefore(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB);
      content.addBefore(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB);
      content.addBefore(Items.MANGROVE_SLAB, VERTICAL_MANGROVE_SLAB);
      content.addBefore(Items.CHERRY_SLAB, VERTICAL_CHERRY_SLAB);
      content.addBefore(Items.BAMBOO_SLAB, VERTICAL_BAMBOO_SLAB);
      content.addBefore(Items.BAMBOO_MOSAIC_SLAB, VERTICAL_BAMBOO_MOSAIC_SLAB);
      content.addBefore(Items.MUD_BRICK_SLAB, VERTICAL_MUD_BRICK_SLAB);
    });
  }
}
