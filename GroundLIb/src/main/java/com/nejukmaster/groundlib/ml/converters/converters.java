package com.nejukmaster.groundlib.ml.converters;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Snowable;

public class converters {

	public static float[][] testConverter(Material[][] blocks){
		float[][] input = new float[28][28];
		for(int i = 0; i <28; i ++) {
			for(int j = 0; j <28; j ++) {
				Material b = blocks[i][j];
				if(b!=null) {
					if(b.equals(Material.BLACK_WOOL))
						input[i][j] = 1.0f;
					else if(b.equals(Material.GRAY_WOOL))
						input[i][j] = 0.6f;
					else if(b.equals(Material.LIGHT_GRAY_WOOL))
						input[i][j] = 0.3f;
					else
						input[i][j] = 0.0f;
				}
				else
					input[i][j] = 0.0f;
			}
		}
		return input;
	}
	
	public static String BlockToStr(Block b) {
		if(b!=null) {
			String[] b_str_arr = b.getType().toString().split("_");
			//grass block for sectioning to snowy or not
			if(b.getType().equals(Material.GRASS_BLOCK)) {
				if(((Snowable)b.getBlockData()).isSnowy())
					return "GRASS_BLOCK_SNOW";
				return Material.GRASS_BLOCK.toString();
			}
			//stained glasses panes
			else if(b_str_arr[1].equalsIgnoreCase("STAINED")
					&& b_str_arr[2].equalsIgnoreCase("GLASS")
					&& b_str_arr[3].equalsIgnoreCase("PANE")) {
				return b.getType().toString()+"_POST";
			}
			//flower separated texture "top" and "bottom"
			else if(b.getType().equals(Material.LILAC)
					|| b.getType().equals(Material.SUNFLOWER)
					|| b.getType().equals(Material.ROSE_BUSH)) {
				return b.getType().toString()+"_TOP";
			}
			else
				return b.getType().toString();
		}
		else
			return Material.AIR.toString();
	}
	
	public static float[][][][][] groundConverter(float[][][] blocks){
		float[][][][][] scaled = new float[1][blocks.length][blocks[0].length][blocks[0][0].length][1];
		for(int i = 0; i < blocks.length; i ++) {
			for(int j =0; j <blocks[0].length; j ++) {
				for(int k = 0; k <blocks[0][0].length; k ++) {
					scaled[0][i][j][k][0] = blocks[i][j][k];
				}
			}
		}
		return scaled;
	}

}
