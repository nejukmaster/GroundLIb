package com.nejukmaster.groundlib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.tensorflow.Tensor;

public class utils {
	
	public static void writeFile(File file, String towrite) {
		FileWriter writer = null;
		try {
            writer = new FileWriter(file, false);
            writer.write(towrite);
            writer.flush();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void writeFile(File file, String towrite, boolean append) {
		if(append == false)
			writeFile(file,towrite);
		else {
			FileWriter writer = null;
			try {
            	writer = new FileWriter(file, true);
            	writer.write(towrite);
            	writer.flush();
        	} catch(IOException e) {
            	e.printStackTrace();
        	} finally {
            	try {
                	if(writer != null) writer.close();
            	} catch(IOException e) {
                	e.printStackTrace();
            	}
        	}
        }
	}
	
	public static void createFile(File file) {
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e1) {
			e1.printStackTrace();
			}
		}
	}
	
	public static void mkDir(File dir) {
		if (!dir.exists()) {
			try{
			    dir.mkdir();
		        }
		        catch(Exception e){
			    e.getStackTrace();
			}
		}
	}
	
	public static void createFile(File file, String towrite) {
		if(!file.exists()){
			try {
				file.createNewFile();
				writeFile(file, towrite);
			} catch (IOException e1) {
			e1.printStackTrace();
			}
		}
	}
	
	public static ArrayList<String> readFile(File file) {
		Scanner reader = null;
		ArrayList<String> content = new ArrayList<>();
		try {
			reader = new Scanner(file);
			while(reader.hasNextLine()) {
				String s = reader.nextLine();
				if(!s.isEmpty())
					content.add(s);
			}
		}catch(IOException e) {
            e.printStackTrace();
        }
		return content;
	}
	
	public static HashMap<String, String> loadConfig(File file) {
		HashMap<String, String> result = new HashMap<>();
		ArrayList<String> content = readFile(file);
		for(String s : content) {
			String[] _s = s.split(":");
			result.put(_s[0], _s[1]);
		}
		return result;
	}
	
	public static ArrayList<String> captureGround(Location s, int r) {
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < r; i ++) {
			for(int j = 0; j < r; j ++) {
				for(int k = 0; k < r; k ++) {
					result.add(s.add(new Location(s.getWorld(),k,j,i)).getBlock().toString());
				}
			}
		}
		if(result != null)
			return result;
		else
			return null;
	}
	
	public static File writeCapture(ArrayList<String> cap, String name) {
		String r = "";
		Iterator<String> i = cap.iterator();
		while(i.hasNext()) {
			r = r+"\""+i.next()+"\"";
			if(i.hasNext())
				r = r+",";
		}
		r = "["+r+"]";
		File f = new File(main.main_dir.getPath()+"/"+name+".gmodel");
		writeFile(f, r);
		return f;
	}
	
	public static int argmax(float[] num_array) {
		int r = 0;
		for(int i = 0; i < num_array.length; i ++) {
			if(num_array[i] > num_array[0]) 
				r = i;
		}
		return r;
	}
	
	public static float[] ObjectToFloatArray(Object[] objs) {
		float[] result = new float[objs.length];
		for(int i = 0; i < objs.length; i ++) {
			result[i] = ((Float)objs[i]).floatValue();
		}
		return result;
	}
	
	public static ItemStack createGuiItem(final Material material, final String name, final String... lore)
    {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
}
