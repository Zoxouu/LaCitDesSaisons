package fr.zoxymodz.laciteedessaisons.events;

import fr.zoxymodz.laciteedessaisons.Main;
import fr.zoxymodz.laciteedessaisons.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class ModItemInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(!PlayerManager.isInModerationMod(player)) return;
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;

        switch(player.getInventory().getItemInHand().getType()){
            case COMPASS:
                List<Player> list = Bukkit.getOnlinePlayers().stream().filter(player1 -> !PlayerManager.isInModerationMod(player1)).collect(Collectors.toList());
                list.remove(player);

                if(list.size() == 0){
                    player.sendMessage(Main.getInstance().getPrefix() +" §cIl n'y a aucun joueur sur le quelle vous téléporter.");
                    return;
                }

                Player target = list.get(new Random().nextInt(list.size()));
                player.teleport(target.getLocation());
                player.sendMessage(Main.getInstance().getPrefix() +" §a Vous avez été teleporter à §b"+ target.getName());
                break;

            case NETHER_STAR:
                PlayerManager mod = PlayerManager.getFPlayer(player);
                mod.setVanish(!mod.isVanished());
                player.sendMessage(mod.isVanished() ? Main.getInstance().getPrefix() +" §a Vanish §2ON" : Main.getInstance().getPrefix() +" §c Vanish §4OFF");
                Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(player));
                break;

        }
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        for(Player players : Bukkit.getOnlinePlayers()){
            if(PlayerManager.isInModerationMod(players)){
                PlayerManager pm = PlayerManager.getFPlayer(players);
                if(pm.isVanished()) players.hidePlayer(player);
            }
        }
    }
}
