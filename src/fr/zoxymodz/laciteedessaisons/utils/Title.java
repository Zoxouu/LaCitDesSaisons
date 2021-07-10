package fr.zoxymodz.laciteedessaisons.utils;

import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Title
{
    private String title;
    private String subtitle;

    public Title(String title) {
        this.title = title;
        this.subtitle = "";
    }






    public Title(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }








    public void send(Player player, int fadeIn, int stay, int fadeOut) {
        PlayerConnection connection = (((CraftPlayer)player).getHandle()).playerConnection;

        PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (IChatBaseComponent)null, fadeIn * 20, stay * 20, fadeOut * 20);
        connection.sendPacket((Packet)packetPlayOutTimes);
        if (this.subtitle != null) {
            this.subtitle = this.subtitle.replaceAll("%player%", player.getDisplayName());
            this.subtitle = ChatColor.translateAlternateColorCodes('&', this.subtitle);
            IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + this.subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket((Packet)packetPlayOutSubTitle);
        }
        if (this.title != null) {
            this.title = this.title.replaceAll("%player%", player.getDisplayName());
            this.title = ChatColor.translateAlternateColorCodes('&', this.title);
            IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + this.title + "\"}");
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket((Packet)packetPlayOutTitle);
        }
    }








    public void send(Collection<? extends Player> players, int fadeIn, int stay, int fadeOut) {
        players.forEach(p -> send(p, fadeIn, stay, fadeOut));
    }
}
