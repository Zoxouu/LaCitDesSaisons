package fr.zoxymodz.laciteedessaisons.rank;

import fr.zoxymodz.laciteedessaisons.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rank {

    private Scoreboard scoreboard;
    private final Plugin plugin;
    private final Map<String, RankList> playerRank = new HashMap<>();
    private FileConfiguration config;
    private File file;

    public Rank(Plugin plugin) {
        this.plugin = plugin;
        initConfig();
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void initScordboard() {
        if (scoreboard != null) throw new UnsupportedOperationException("Le Scoreboard est deja initialise.");
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        for (RankList rankList : RankList.values()) {
            Team team = scoreboard.registerNewTeam(rankList.getName());
            team.setPrefix(rankList.getPrefix());
            team.setSuffix(rankList.getSuffix());
        }
    }

    private void initConfig(){
        File f = new File("plugins/LaCiteDesSaisons");
        if (!f.exists()) f.mkdir();
        file = new File(f, "rank.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioe) {ioe.printStackTrace(); }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void loadPlayer(Player p) {
        String uuid = p.getUniqueId().toString();
        if (playerRank.containsKey(uuid)) {
            Main.getInstance().getLogger().info("Player already exist.");
            return;
        }
        if (!config.contains(uuid)){
            playerRank.put(uuid, RankList.DEFAULT);
            saveConfig();
            return;
        }
        playerRank.put(uuid, getRankById(config.getInt(uuid)));
    }

    public void setPlayerRank(Player p, RankList rank) {
        playerRank.put(p.getUniqueId().toString(), rank);
    }

    public void deletePlayer(Player p) {
        if (!playerRank.containsKey(p.getUniqueId().toString())) return;
        playerRank.remove(p.getUniqueId().toString());
    }

    public RankList getPlayerRank(Player p) {
        if (!playerRank.containsKey(p.getUniqueId().toString())) loadPlayer(p);
        return playerRank.get(p.getUniqueId().toString());
    }

    public RankList getRank(String rank) {
        try {
            return RankList.valueOf(rank.toUpperCase());
        } catch (IllegalArgumentException e) {
            try {
                int rankID = Integer.parseInt(rank);
                return getRankById(rankID);
            } catch (NumberFormatException nfe) {
                return RankList.DEFAULT;
            }
        }
    }

    public RankList getRankById(int id) {
        return Arrays.stream(RankList.values()).filter(rankList -> rankList.getId() == id)
                .findFirst().get();
    }

    public void removeRankFromPlayer(Player p) {
        setPlayerRank(p, RankList.DEFAULT);
    }

    public void saveConfig() {
        playerRank.forEach((s, rank) -> config.set(s, rank.getId()));
        try {
            config.save(file);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
