package fr.zoxymodz.laciteedessaisons.manager;

import fr.zoxymodz.laciteedessaisons.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {
	
	private Player player;
	private ItemStack[] items = new ItemStack[40];
	private boolean vanished;
	
	public PlayerManager(Player player) {
		this.player = player;
		vanished = false;
		}
	public void init() {
		Main.getInstance().players.put(player.getUniqueId(), this);
	}
	
	public void destroy() {
		Main.getInstance().players.remove(player.getUniqueId());
	}
	
	public static PlayerManager getFPlayer(Player player) {
		return Main.getInstance().players.get(player.getUniqueId());
	}
	
	public static boolean isInModerationMod(Player player) {
		return Main.getInstance().moderateur.contains(player.getUniqueId());
	}
	
	public ItemStack[] getItem() {
		return items;
	}

	public boolean isVanished() {
		return vanished;
	}

	public void setVanish(boolean vanished){
		this.vanished = vanished;
		if(vanished){
			Bukkit.getOnlinePlayers().forEach(players ->players.hidePlayer(player));
		}else {
			Bukkit.getOnlinePlayers().forEach(players ->players.showPlayer(player));
		}
	}

	public void saveInventory() {
		for(int slot = 0; slot < 36; slot++){
			ItemStack item = player.getInventory().getItem(slot);
			if(item !=null) {
				items[slot] = item;
			}
		}
		
		items[36] = player.getInventory().getHelmet();
		items[37] = player.getInventory().getChestplate();
		items[38] = player.getInventory().getLeggings();
		items[39] = player.getInventory().getBoots();
		
		player.getInventory().clear();
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
	}
	
	public void giveInventory() {
		player.getInventory().clear();
		for(int slot = 0; slot < 36; slot++){
			ItemStack item = items[slot];
			if(item != null);
			player.getInventory().setItem(slot, item);
		}
		player.getInventory().setHelmet(items[36]);
		player.getInventory().setChestplate(items[37]);
		player.getInventory().setLeggings(items[38]);
		player.getInventory().setBoots(items[39]);
	}
	
	
}