package fr.zoxymodz.laciteedessaisons.commands;

import fr.zoxymodz.laciteedessaisons.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CiteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String agrs, String[] label) {

        Player p = (Player) Sender;
        Location spawn = Bukkit.getWorld("world").getSpawnLocation();

        if (!(Sender instanceof Player)) {
            Sender.sendMessage("Seul un joueur peut executer cette commande !");
        } else {
            p.teleport(spawn);
            p.sendMessage(Main.instance.getPrefix() + " §eTu a bien été téléporter a la cité !");
        }
        return (false);
    }
}