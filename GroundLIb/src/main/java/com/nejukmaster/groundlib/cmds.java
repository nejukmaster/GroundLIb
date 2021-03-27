package com.nejukmaster.groundlib;

import java.awt.Event;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.function.Supplier;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.nejukmaster.groundlib.exceptions.MPP_DOESNT_VALIDATE_EXCEPTION;
import com.nejukmaster.groundlib.exceptions.NO_MPP_EXIST_EXCEPTION;
import com.nejukmaster.groundlib.ml.ML;
import com.nejukmaster.groundlib.ml.MLUtils;
import com.nejukmaster.groundlib.ml.Model;
import com.nejukmaster.groundlib.ml.converters.BlockToColorConverter;
import com.nejukmaster.groundlib.ml.converters.converters;

import net.md_5.bungee.api.ChatColor;

public class cmds implements CommandExecutor{

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(args[0].equalsIgnoreCase("capture")) {
				if(args.length<2)
					p.sendMessage(ChatColor.RED+"/gl capture [class(integer)] [name]");
				else {
					Location sp = p.getLocation().add(new Location(p.getWorld(),-10,-10,-10));
					Block[][][] b = new Block[20][20][20];
					for(int i = 0; i < 20; i ++) {
						for(int j = 0; j < 20; j ++) {
							for(int k = 0; k < 20; k ++) {
								b[i][j][k] = sp.clone().add(new Location(sp.getWorld(),i,j,k)).getBlock();
							}
						}
					}
					MLUtils.extractSample(args[2], Integer.parseInt(args[1]), b);
				}
			}
			if(args[0].equalsIgnoreCase("record")) {
				if(args.length < 5)
					p.sendMessage(ChatColor.RED+"/gl record [original name] [class(integer)] [count] [per(tick)]");
				else {
					int count = Integer.parseInt(args[3]);
					int per = Integer.parseInt(args[4]);
					if(events.marking == null)
						events.marking = p;
					RepeatingTask repeatingTask = new RepeatingTask(main.getPlugin(main.class), 0, per, count) {
			            @Override
			            public void run() {
			            		if(this.count > 0) {
			            			MLUtils.recordSample(events.marking, args[1], Integer.parseInt(args[2]), this.count);
			            			this.count--;
			            		}
			            	}
			        };
				}
			}
			if(args[0].equalsIgnoreCase("marker")) {
				p.getInventory().addItem(events.marker);
			}
			if(args[0].equalsIgnoreCase("ground")) {
				Location sp = p.getLocation().add(new Location(p.getWorld(),-10,-10,-10));
				Block[][][] blocks = new Block[20][20][20];
				for(int i = 0; i < 20; i ++) {
					for(int j = 0; j < 20; j ++) {
						for(int k = 0; k < 20; k ++) {
							blocks[i][j][k] = sp.clone().add(new Location(sp.getWorld(),i,j,k)).getBlock();
						}
					}
				}
				float[][][] example = new float[blocks[0].length][blocks[0][0].length][blocks.length*4];
				for(int i = 0; i < blocks.length; i ++) {
					for(int j = 0; j < blocks[0].length; j ++) {
						for(int k = 0; k < blocks[0][0].length; k ++) {
							BlockToColorConverter converter = new BlockToColorConverter(blocks[i][j][k]);
							example[j][k][i*4] = converter.get()[0]/255f;
							example[j][k][i*4+1] = converter.get()[1]/255f;
							example[j][k][i*4+2] = converter.get()[2]/255f;
							example[j][k][i*4+3] = converter.get()[3]/255f;
						}
					}
				}
				float[][][][][] scaled = converters.groundConverter(example);
				try {
					Model model = new Model("cnn_for_minecraft_mat");
					FloatBuffer fb = (FloatBuffer)(model.predict(scaled).get());
					p.sendMessage(fb.array()[0]+"");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(args[0].equalsIgnoreCase("test")) {
				Material[][] blocks = new Material[28][28];
				int x_coe = 0,z_coe = 0;
				Location sp = events.sp;
				Location ep = events.ep;
				if(Math.abs(sp.getBlockX() - ep.getBlockX()) == 27 && Math.abs(sp.getBlockZ() - ep.getBlockZ()) == 27) {
					x_coe = sp.getBlockX() > ep.getBlockX()? -1 : 1;
					z_coe = sp.getBlockZ() > ep.getBlockZ()? -1 : 1;
				}
				else {
					p.sendMessage("Please selecte range of 28X28.");
					return false;
				}
				for(int i = 0; i <28; i++) {
					for(int j = 0; j <28; j ++) {
						Location l = new Location(sp.getWorld(),sp.getX()+x_coe*i,sp.getY(),sp.getZ()+z_coe*j);
						blocks[i][j] = l.getBlock().getType();
					}
				}
				try {
					Model model = new Model("saved_model");
					FloatBuffer fb = (FloatBuffer)(model.predict(converters.testConverter(blocks)).get());
					p.sendMessage(ML.FashionMNistClasses[utils.argmax(fb.array())]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	
}
