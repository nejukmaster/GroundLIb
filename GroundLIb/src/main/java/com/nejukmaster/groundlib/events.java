package com.nejukmaster.groundlib;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class events implements Listener{
	
	public static Location sp = null;
	public static Location ep = null;
	public static LivingEntity marking = null;
	public static ItemStack marker = utils.createGuiItem(Material.BLAZE_ROD, "Marking Rod", "Hit entity would be marker");
	
	@EventHandler
	public void ineractHook(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		Block b = e.getClickedBlock();
		if(e.getHand() == EquipmentSlot.HAND) {
			ItemStack is = p.getInventory().getItemInMainHand().clone();
			if(is!=null&&is.getType().equals(Material.WOODEN_HOE)) {
				if(a.equals(Action.LEFT_CLICK_BLOCK))
					sp = b.getLocation();
				else if(a.equals(Action.RIGHT_CLICK_BLOCK))
					ep = b.getLocation();
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
    public void onPlayerAttacked(EntityDamageByEntityEvent e) {
        if(e.getDamager().getType() == EntityType.PLAYER && e.getEntity() instanceof LivingEntity) {
            Player p = (Player) e.getDamager();
            ItemStack is = p.getInventory().getItemInMainHand().clone();
            if(is.equals(marking)) {
            	marking = (LivingEntity)e.getEntity();
            	e.setCancelled(true);
            }
        }
    }

}
