package com.nejukmaster.groundlib;

import java.io.File;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
	public static File main_dir = new File("plugins/GroundLib");
	public static File scan_dir = new File("plugins/GroundLib/Scans");
	public static File model_dir = new File("plugins/GroundLib/Models");
	public static File resource_dir = new File("plugins/GroundLib/Resources");
	
	public void onEnable() {
		System.out.println("GroundLib-1.0.0");
		utils.mkDir(main_dir);
		utils.mkDir(scan_dir);
		utils.mkDir(model_dir);
		initCmds();
		initEvents();
	}

	public void initCmds() {
		getCommand("gl").setExecutor(new cmds());
	}
	
	public void initEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new events(), this);
	}
}
