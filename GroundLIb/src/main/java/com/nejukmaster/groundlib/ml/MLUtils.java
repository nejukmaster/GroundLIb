package com.nejukmaster.groundlib.ml;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import com.nejukmaster.groundlib.main;
import com.nejukmaster.groundlib.utils;
import com.nejukmaster.groundlib.ml.converters.BlockToColorConverter;

import net.md_5.bungee.api.ChatColor;

public class MLUtils {
	
	public static void extractSample(String name,int c, Block[][][] blocks) {
		File f = new File(main.scan_dir+"/"+name+".sample");
		int[][][] example = new int[blocks.length*4][blocks[0].length][blocks[0][0].length];
		for(int i = 0; i < blocks.length; i ++) {
			for(int j = 0; j < blocks[0].length; j ++) {
				for(int k = 0; k < blocks[0][0].length; k ++) {
					BlockToColorConverter converter = new BlockToColorConverter(blocks[i][j][k]);
					example[i*4][j][k] = converter.get()[0];
					example[i*4+1][j][k] = converter.get()[1];
					example[i*4+2][j][k] = converter.get()[2];
					example[i*4+3][j][k] = converter.get()[3];
				}
			}
		}
		String s = "";
		for(int i = 0; i < example.length; i ++) {
			for(int j = 0; j < example[0].length; j ++) {
				for(int k = 0; k < example[0][0].length; k ++) {
					s += example[i][j][k];
					if(j != example[0].length || k != example[0][0].length)
						s += ":";
				}
			}
			s +="/";
		}
		s += c+"";
		utils.writeFile(f, s);		
	}
	
	public static void recordSample(LivingEntity mark, String name, int c, int count) {
		Location sp = mark.getLocation().add(new Location(mark.getWorld(),-10,-10,-10));
		Block[][][] b = new Block[20][20][20];
		for(int i = 0; i < 20; i ++) {
			for(int j = 0; j < 20; j ++) {
				for(int k = 0; k < 20; k ++) {
					b[i][j][k] = sp.clone().add(new Location(sp.getWorld(),i,j,k)).getBlock();
				}
			}
		}
		MLUtils.extractSample(name+count, c, b);
	}

}
