package com.nejukmaster.groundlib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RepeatingTask implements Runnable{
	
	private int taskId;
	public int count;

    public RepeatingTask(JavaPlugin plugin, int arg1, int arg2, int count) {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, arg1, arg2);
        this.count = count;
    }

    public void canncel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
