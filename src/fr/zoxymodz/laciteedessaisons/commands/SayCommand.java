package fr.zoxymodz.laciteedessaisons.commands;

import fr.zoxymodz.laciteedessaisons.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SayCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("say")) {

            Player player = (Player)sender;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                builder.append(args[i]).append(" ");
            }
            String message = builder.toString();
            Title title = new Title("§f» §6Annonce", "§c§l" + message);

            if (sender instanceof Player) {

                if (args.length == 0) {
                    player.sendMessage("§c/say <MESSAGE>");
                    return false;
                }

                if (player.hasPermission("lacitedessaison.use.say")) {
                    for (Player pls : Bukkit.getOnlinePlayers())
                    {
                        title.send(pls.getPlayer(), 1, 7, 1);
                    }
                }
            }
        }

        return false;
    }
}
