package fr.zoxymodz.laciteedessaisons.commands;

import fr.zoxymodz.laciteedessaisons.Main;
import fr.zoxymodz.laciteedessaisons.utils.ItemBuilder;
import fr.zoxymodz.laciteedessaisons.manager.PlayerManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
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
            if (PlayerManager.isInModerationMod(p)) {
                PlayerManager pm = PlayerManager.getFPlayer(p);

                Main.getInstance().moderateur.remove(p.getUniqueId());
                p.getInventory().clear();
                p.sendMessage(Main.getInstance().getPrefix() +" §c Vous avez quitter votre service !");
                pm.giveInventory();
                p.setInvulnerable(false);
                p.setAllowFlight(false);
                pm.destroy();
                return false;
            }

            PlayerManager pm = new PlayerManager(p);
            pm.init();

            Main.getInstance().moderateur.add(p.getUniqueId());
            p.sendMessage(Main.getInstance().getPrefix() +" §e Vous avez pris votre service !");
            p.setInvulnerable(true);
            pm.saveInventory();
            p.setAllowFlight(true);

            ItemBuilder rdp = new ItemBuilder(Material.COMPASS).setName("§9Random player").setLore("§7Clique droit pour ce", "§7tp sur un joueur random");
            ItemBuilder vanish = new ItemBuilder(Material.NETHER_STAR).setName("§aVanish").setLore("§7Clique droit pour ce", "§7rendre invisible");

            p.getInventory().setItem(8, vanish.toItemStack());
            p.getInventory().setItem(4, rdp.toItemStack());
            return (false);
        }
}