package fr.zoxymodz.laciteedessaisons.commands;

import fr.zoxymodz.laciteedessaisons.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String args, String[] label) {

        Player p = (Player) Sender;

        if (!(Sender instanceof Player)) {
            Sender.sendMessage("Seul un joueur peut executer cette commande !");
        }
        if (!p.hasPermission("moderation.mod")) {
            p.sendMessage(Main.getInstance().getPrefix() +" §cUnknown command !");
            return false;
        }
        if (p.getAllowFlight()) {
            p.setAllowFlight(false);
            p.sendMessage(Main.getInstance().getPrefix() +" §ctu n'es maintenant plus fly.");
        } else {
            p.setAllowFlight(true);
            p.sendMessage(Main.getInstance().getPrefix() +" §etu es maintenant en fly.");
        }
        return false;
    }
}
