package com.nejukmaster.groundlib.ml.converters;

import java.awt.Color;
import java.io.File;
import java.util.function.Supplier;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Snowable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nejukmaster.groundlib.main;
import com.nejukmaster.groundlib.utils;

public class BlockToColorConverter implements Supplier<Integer[]>{
	
	int R;
	int G;
	int B;
	int Alpha;
	
	public BlockToColorConverter(Block block) {
		if(block != null && block.getType() != Material.AIR) {
			Material t = block.getType();
			if(t.equals(Material.GRASS_BLOCK)) {
				if(((Snowable)block.getBlockData()).isSnowy()) 
					setRGB(255,255,255,0);
				else 
					setRGB(106,170,64,0);
			}
			else if(t.equals(Material.GRASS_PATH)) 
				setRGB(144,117,64,0);
			else if(t.equals(Material.BEDROCK))
				setRGB(0,0,0,0);
			else if(t.equals(Material.ACACIA_DOOR)||
					t.equals(Material.ACACIA_FENCE)||
					t.equals(Material.ACACIA_FENCE_GATE)||
					t.equals(Material.ACACIA_PLANKS)||
					t.equals(Material.ACACIA_SLAB)||
					t.equals(Material.ACACIA_STAIRS)) 
				setRGB(186,99,55,0);
			else if(t.equals(Material.DARK_OAK_DOOR)||
					t.equals(Material.DARK_OAK_FENCE)||
					t.equals(Material.DARK_OAK_FENCE_GATE)||
					t.equals(Material.DARK_OAK_PLANKS)||
					t.equals(Material.DARK_OAK_SLAB)||
					t.equals(Material.DARK_OAK_STAIRS)) 
				setRGB(79,50,24,0);
			else if(t.equals(Material.OAK_DOOR)||
					t.equals(Material.OAK_FENCE)||
					t.equals(Material.OAK_FENCE_GATE)||
					t.equals(Material.OAK_PLANKS)||
					t.equals(Material.OAK_SLAB)||
					t.equals(Material.OAK_STAIRS)) 
				setRGB(184,148,95,0);
			else if(t.equals(Material.BIRCH_DOOR)||
					t.equals(Material.BIRCH_FENCE)||
					t.equals(Material.BIRCH_FENCE_GATE)||
					t.equals(Material.BIRCH_PLANKS)||
					t.equals(Material.BIRCH_SLAB)||
					t.equals(Material.BIRCH_STAIRS)) 
				setRGB(215,193,133,0);
			else if(t.equals(Material.JUNGLE_DOOR)||
					t.equals(Material.JUNGLE_FENCE)||
					t.equals(Material.JUNGLE_FENCE_GATE)||
					t.equals(Material.JUNGLE_PLANKS)||
					t.equals(Material.JUNGLE_SLAB)||
					t.equals(Material.JUNGLE_STAIRS)) 
				setRGB(170,121,84,0);
			else if(t.equals(Material.SPRUCE_DOOR)||
					t.equals(Material.SPRUCE_FENCE)||
					t.equals(Material.SPRUCE_FENCE_GATE)||
					t.equals(Material.SPRUCE_PLANKS)||
					t.equals(Material.SPRUCE_SLAB)||
					t.equals(Material.SPRUCE_STAIRS)) 
				setRGB(122,90,52,0);
			else if(t.equals(Material.CRIMSON_DOOR)||
					t.equals(Material.CRIMSON_FENCE)||
					t.equals(Material.CRIMSON_FENCE_GATE)||
					t.equals(Material.CRIMSON_PLANKS)||
					t.equals(Material.CRIMSON_SLAB)||
					t.equals(Material.CRIMSON_STAIRS)) 
				setRGB(126,58,86,0);
			else if(t.equals(Material.WARPED_DOOR)||
					t.equals(Material.WARPED_FENCE)||
					t.equals(Material.WARPED_FENCE_GATE)||
					t.equals(Material.WARPED_PLANKS)||
					t.equals(Material.WARPED_SLAB)||
					t.equals(Material.WARPED_STAIRS)) 
				setRGB(40,112,130,0);
			else if(t.equals(Material.STONE)||
					t.equals(Material.STONE_SLAB)||
					t.equals(Material.STONE_STAIRS)||
					t.equals(Material.STONE_BRICKS)||
					t.equals(Material.SMOOTH_STONE)||
					t.equals(Material.CRACKED_STONE_BRICKS)||
					t.equals(Material.CHISELED_STONE_BRICKS)||
					t.equals(Material.STONECUTTER)||
					t.equals(Material.COBBLESTONE))
				setRGB(143,143,143,0);
			else if(t.equals(Material.MOSSY_STONE_BRICKS)||
					t.equals(Material.MOSSY_STONE_BRICK_SLAB)||
					t.equals(Material.MOSSY_STONE_BRICK_STAIRS)||
					t.equals(Material.MOSSY_STONE_BRICK_WALL)||
					t.equals(Material.MOSSY_COBBLESTONE)||
					t.equals(Material.MOSSY_COBBLESTONE_SLAB)||
					t.equals(Material.MOSSY_COBBLESTONE_STAIRS)||
					t.equals(Material.MOSSY_COBBLESTONE_WALL))
				setRGB(82,93,57,0);
			//write ore to little more dark color than stone.
			else if(t.equals(Material.COAL_ORE)||
					t.equals(Material.IRON_ORE)||
					t.equals(Material.GOLD_ORE)||
					t.equals(Material.REDSTONE_ORE)||
					t.equals(Material.DIAMOND_ORE)||
					t.equals(Material.LAPIS_ORE)||
					t.equals(Material.EMERALD_ORE))
				setRGB(52,52,52,0);
			/*else if(t.equals(Material.END_STONE)||
					t.equals(Material.END_STONE_BRICKS)||
					t.equals(Material.END_STONE_BRICK_SLAB)||
					t.equals(Material.END_STONE_BRICK_STAIRS)||
					t.equals(Material.END_STONE_BRICK_WALL))
				setRGB(246,250,189,0);
			else if(t.equals(Material.BLACKSTONE)||
					t.equals(Material.BLACKSTONE_SLAB)||
					t.equals(Material.BLACK_TERRACOTTA)||
					t.equals(Material.BLACK_WOOL)||
					t.equals(Material.BLACK_CONCRETE)||
					t.equals(Material.BLACK_CONCRETE_POWDER)||
					t.equals(Material.CHISELED_POLISHED_BLACKSTONE)||
					t.equals(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS)||
					t.equals(Material.POLISHED_BLACKSTONE_BRICKS)||
					t.equals(Material.POLISHED_BLACKSTONE_BRICK_SLAB)||
					t.equals(Material.POLISHED_BLACKSTONE_BRICK_STAIRS)||
					t.equals(Material.POLISHED_BLACKSTONE_BRICK_WALL)||
					t.equals(Material.BLACK_SHULKER_BOX))
				setRGB(22,15,16,0);*/
			else if(t.equals(Material.POLISHED_BASALT))
				setRGB(116,116,116,0);
			else if(t.equals(Material.GRANITE)||
					t.equals(Material.GRANITE_SLAB)||
					t.equals(Material.GRANITE_STAIRS)||
					t.equals(Material.POLISHED_GRANITE)||
					t.equals(Material.POLISHED_GRANITE_SLAB)||
					t.equals(Material.POLISHED_GRANITE_STAIRS))
				setRGB(159,107,88,0);
			/*else if(t.equals(Material.RED_TERRACOTTA)||
					t.equals(Material.RED_GLAZED_TERRACOTTA)||
					t.equals(Material.RED_SHULKER_BOX)||
					t.equals(Material.RED_CONCRETE))
				setRGB(156,37,34,0);
			else if(t.equals(Material.RED_WOOL)||
					t.equals(Material.RED_CONCRETE_POWDER))
				setRGB(173,44,36,0);
			else if(t.equals(Material.YELLOW_TERRACOTTA))
				setRGB(186,134,36,0);
			else if(t.equals(Material.YELLOW_CONCRETE_POWDER))
				setRGB(246,246,61,0);
			else if(t.equals(Material.YELLOW_CONCRETE))
				setRGB(241,175,21,0);
			else if(t.equals(Material.YELLOW_WOOL)||
					t.equals(Material.YELLOW_SHULKER_BOX))
				setRGB(254,214,64,0);
			else if(t.equals(Material.GREEN_WOOL))
				setRGB(80,102,30,0);*/
			else if(t.equals(Material.ACACIA_LOG)||
					t.equals(Material.STRIPPED_ACACIA_LOG))
				setRGB(200,191,231,0);
			else if(t.equals(Material.OAK_LOG)||
					t.equals(Material.STRIPPED_OAK_LOG))
				setRGB(95,74,43,0);
			else if(t.equals(Material.DARK_OAK_LOG)||
					t.equals(Material.STRIPPED_ACACIA_LOG))
				setRGB(48,37,19,0);
			else if(t.equals(Material.JUNGLE_LOG)||
					t.equals(Material.STRIPPED_JUNGLE_LOG))
				setRGB(84,77,26,0);
			else if(t.equals(Material.SPRUCE_LOG)||
					t.equals(Material.STRIPPED_SPRUCE_LOG))
				setRGB(59,39,19,0);
			else if(t.equals(Material.DIRT)||
					t.equals(Material.COARSE_DIRT)||
					t.equals(Material.FARMLAND))
				setRGB(150,108,74,0);
			else if(t.equals(Material.MYCELIUM))
				setRGB(147,89,119,0);
			else if(t.equals(Material.PODZOL))
				setRGB(172,102,32,0);
			else if(t.equals(Material.SAND))
				setRGB(227,219,176,0);
			else if(t.equals(Material.SANDSTONE)||
					t.equals(Material.CHISELED_SANDSTONE)||
					t.equals(Material.SMOOTH_SANDSTONE)||
					t.equals(Material.SMOOTH_SANDSTONE_SLAB)||
					t.equals(Material.SMOOTH_SANDSTONE_STAIRS)||
					t.equals(Material.CUT_SANDSTONE_SLAB)||
					t.equals(Material.CUT_SANDSTONE)||
					t.equals(Material.SANDSTONE_SLAB)||
					t.equals(Material.SANDSTONE_STAIRS))
				setRGB(255,249,121,0);
			else if(t.equals(Material.RED_SAND)) 
				setRGB(191,103,33,0);
			else if(t.equals(Material.RED_SANDSTONE)||
					t.equals(Material.CHISELED_RED_SANDSTONE)||
					t.equals(Material.SMOOTH_RED_SANDSTONE)||
					t.equals(Material.SMOOTH_RED_SANDSTONE_SLAB)||
					t.equals(Material.SMOOTH_RED_SANDSTONE_STAIRS)||
					t.equals(Material.CUT_RED_SANDSTONE_SLAB)||
					t.equals(Material.CUT_RED_SANDSTONE)||
					t.equals(Material.RED_SANDSTONE_SLAB)||
					t.equals(Material.RED_SANDSTONE_STAIRS))
				setRGB(164,83,17,0);
			else if(t.equals(Material.CLAY))
				setRGB(175,185,214,0);
			else if(t.equals(Material.WATER))
				setRGB(12,17,211,125);
			else if(t.equals(Material.LAVA))
				setRGB(1216,60,7,0);
			else
				setRGB(0,0,0,255);
		}
		else 
			setRGB(0,0,0,255);
	}
	
	private void setRGB(int R, int G, int B, int Alpha) {
		this.R = R;
		this.G = G;
		this.B = B;
		this.Alpha = Alpha;
	}

	@Override
	public Integer[] get() {
		return new Integer[] {R,G,B,Alpha};
	}

}
