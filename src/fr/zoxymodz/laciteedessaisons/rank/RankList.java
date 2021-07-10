package fr.zoxymodz.laciteedessaisons.rank;

public enum RankList {

    GERANT(100,100, "§7[§4Gérant§7] §c", "", " §8➜ §c"),
    STAFF(50,50, "§7[§6Staff§7] §e", "", " §8➜ §e"),
    BLEU(7, 1,"§7[§9Equipe Bleu§7] ", "", " §8➜ §7"),
    ROUGE(6, 1,"§7[§cEquipe Rouge§7] ", "", " §8➜ §7"),
    VERT(5, 1,"§7[§aEquipe Vert§7] ", "", " §8➜ §7"),
    ROSE(4, 1,"§7[§dEquipe Rose§7] ", "", " §8➜ §7"),
    JAUNE(3, 1,"§7[§eEquipe Jaune§7] ", "", " §8➜ §7"),
    CYAN(2, 1,"§7[§bEquipe Cyan§7] ", "", " §8➜ §7"),
    DEFAULT(1,1, "", "", " §8➜ §7");

    private final int id,power;
    private final String prefix,suffix,chatSeparator;

    RankList(int id,int power, String prefix, String suffix, String chatSeparator){
        this.id = id;
        this.power = power;
        this.prefix = prefix;
        this.suffix = suffix;
        this.chatSeparator = chatSeparator;
    }

    public int getId() {
        return id;
    }

    public int getPower() {
        return power;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getChatSeparator() {
        return chatSeparator;
    }

    public String getName() {
        return this.toString();
    }
}
