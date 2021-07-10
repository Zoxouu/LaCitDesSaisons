package fr.zoxymodz.laciteedessaisons;

import fr.zoxymodz.laciteedessaisons.commands.*;
import fr.zoxymodz.laciteedessaisons.events.ModItemInteract;
import fr.zoxymodz.laciteedessaisons.events.PlayerListener;
import fr.zoxymodz.laciteedessaisons.rank.Rank;
import fr.zoxymodz.laciteedessaisons.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {

    public static Main instance;
    public Map<UUID, PlayerManager> players = new HashMap<>();
    public List<UUID> moderateur = new ArrayList<>();
    private Rank rank;

    @Override
    public void onLoad() {
        rank = new Rank(this);
    }

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        rank.initScordboard();
    }

    @Override
    public void onDisable() {
        rank.saveConfig();
    }

    private void registerCommands(){
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("cite").setExecutor(new CiteCommand());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("t").setExecutor(new ChatCommand());
        getCommand("say").setExecutor(new SayCommand());
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ModItemInteract(), this);
        pm.registerEvents(new PlayerListener(), this);
    }

    public Map<UUID, PlayerManager> getPlayers() {
        return players;
    }

    public String getPrefix(){
        return "§6Cité §7➢ ";
    }

    public Rank getRank() {
        return rank;
    }

    public static Main getInstance() {
        return instance;
    }
}
