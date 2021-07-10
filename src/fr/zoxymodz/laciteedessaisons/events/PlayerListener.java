package fr.zoxymodz.laciteedessaisons.events;

import fr.zoxymodz.laciteedessaisons.Main;
import fr.zoxymodz.laciteedessaisons.rank.RankList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        Main.getInstance().getRank().loadPlayer(e.getPlayer());
        //e.getPlayer().setScoreboard(Main.getInstance().getRank().getScoreboard());
        e.getPlayer().setDisplayName(Main.getInstance().getRank().getPlayerRank(e.getPlayer()).getPrefix() + e.getPlayer().getName());
        e.getPlayer().setPlayerListName(Main.getInstance().getRank().getPlayerRank(e.getPlayer()).getPrefix() + e.getPlayer().getName());
        p.setPlayerListHeaderFooter("\n  §f❱ §6La Cité Des Saisons \n", "\n Notre teamspeak est §bts.yzonianetwork.net");

    }

    @EventHandler
    private void onQuit(PlayerQuitEvent e) {
        //Main.getInstance().getRank().deletePlayer(e.getPlayer());
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent e) {
        RankList rankList = Main.getInstance().getRank().getPlayerRank(e.getPlayer());
        e.setFormat(rankList.getPrefix()+e.getPlayer().getName()+rankList.getSuffix()+rankList.getChatSeparator()+e.getMessage());
    }
}
