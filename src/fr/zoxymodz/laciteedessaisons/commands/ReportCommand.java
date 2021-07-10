package fr.zoxymodz.laciteedessaisons.commands;

import fr.zoxymodz.laciteedessaisons.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String args, String[] label) {

        Player p = (Player) Sender;

        if (!(Sender instanceof Player)) {
            Sender.sendMessage("Seul un joueur peut executer cette commande !");
        }
            if (label.length != 1) {
                p.sendMessage(Main.getInstance().getPrefix() +" §cPlease enter the nickname of a player !");
                return false;
            }
            String targetName = label[0];

            if (Bukkit.getPlayer(targetName) == null) {
                p.sendMessage(Main.getInstance().getPrefix() +" §cThis player is not logged in or does not exist !");
            }

            if (p.hasPermission("moderation.mod")) {
                p.sendMessage("§6Report §7➢ §c"+ targetName +" §evous a été signalé par §7"+ p.getDisplayName());
            }

        return false;
    }
}
