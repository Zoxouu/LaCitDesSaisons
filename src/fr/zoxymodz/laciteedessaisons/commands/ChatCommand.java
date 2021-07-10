package fr.zoxymodz.laciteedessaisons.commands;

import fr.zoxymodz.laciteedessaisons.Main;
import fr.zoxymodz.laciteedessaisons.rank.RankList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage("Seul un joueur peut executer cette commande !");
            return false;
        } else {
            Player p = (Player) sender;
            StringBuilder s = new StringBuilder();
            for (String a : args) {
                s.append(a).append(" ");
            }
            RankList r = Main.getInstance().getRank().getPlayerRank(p);
            if (r.equals(RankList.GERANT) || r.equals(RankList.STAFF) || r.equals(RankList.DEFAULT)) {
                sender.sendMessage("Vous ne pouvez pas utiliser cette commande.");
            } else {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if (Main.getInstance().getRank().getPlayerRank(players).equals(r)) {
                        players.sendMessage("§7[§8Chat-Team§7] " + r.getPrefix() + p.getName() + " §8➜ §7"+ s.toString());
                    }
                }
            }
        }
        return false;
    }
}
