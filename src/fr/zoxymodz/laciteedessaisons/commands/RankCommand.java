package fr.zoxymodz.laciteedessaisons.commands;

import fr.zoxymodz.laciteedessaisons.Main;
import fr.zoxymodz.laciteedessaisons.rank.RankList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (!player.hasPermission("lacitedessaisons.rank")) {
            player.sendMessage(Main.getInstance().getPrefix() + "vous n'avez pas la permission d'utiliser cette commande.");
            return false;
        }
        if(args.length != 3) {
            player.sendMessage("Erreur");
            return false;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if(target == null) {
            player.sendMessage(Main.getInstance().getPrefix() + "Ce joueur n'existe pas");
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "add":
                RankList rank = Main.getInstance().getRank().getRank(args[2]);
                Main.getInstance().getRank().setPlayerRank(target, rank);
                player.sendMessage(Main.getInstance().getPrefix() + "le rang " + rank.getName().toLowerCase() + " à bien été ajouté au joueur " + target.getName() + " .");
                break;
            case "remove":
                Main.getInstance().getRank().removeRankFromPlayer(target);
                player.sendMessage(Main.getInstance().getPrefix() + "Vous avez bien enlevé le grade de " + target.getName() + " !");
                break;
        }
        return true;
    }
}
