package fun.slowfeew.api.Player.GamePlayer;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import fun.slowfeew.api.Database.Mongo;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class MiniRoyal {
    private MongoCollection<Document> collection;
    private Document playerStats;

    private Integer elo;
    private Integer topelo;
    private Integer win;
    private Integer looses;
    private Integer playedgames;
    private Integer kills;
    private Integer deaths;
    private Integer chestOpened;
    private Integer currentwinstreak;
    private Integer bestwinstreak;

    public MiniRoyal(MongoCollection<Document> collection, String joueur) {
        Document player = Mongo.collec.find(eq("Joueur", joueur)).first();
        if (player != null) {
            this.playerStats = player;
            this.elo = player.getInteger("elo");
            this.topelo = player.getInteger("topelo");
            this.win = player.getInteger("win");
            this.looses = player.getInteger("looses");
            this.playedgames = player.getInteger("playedgames");
            this.kills = player.getInteger("kills");
            this.deaths = player.getInteger("deaths");
            this.chestOpened = player.getInteger("bedbreak");
            this.currentwinstreak = player.getInteger("currentwinstreak");
            this.bestwinstreak = player.getInteger("bestwinstreak");
        } else {
            throw new IllegalArgumentException("Joueur " + joueur + " n'a pas été trouvé dans la collection.");
        }
    }

    public Integer getElo() { return elo; }
    public void setElo(Integer elo) {
        this.elo = elo;
        updateFieldInDb("elo", elo);
    }

    public Integer getTopelo() { return topelo; }
    public void setTopelo(Integer topelo) {
        this.topelo = topelo;
        updateFieldInDb("topelo", topelo);
    }

    public Integer getWin() { return win; }
    public void setVictoire(Integer win) {
        this.win = win;
        updateFieldInDb("win", win);
    }

    public Integer getLooses() { return looses; }
    public void setDefaites(Integer looses) {
        this.looses = looses;
        updateFieldInDb("looses", looses);
    }

    public Integer getPlayedgames() { return playedgames; }
    public void setPlayedgames(Integer playedgames) {
        this.playedgames = playedgames;
        updateFieldInDb("playedgames", playedgames);
    }

    public Integer getKills() { return kills; }
    public void setKills(Integer kills) {
        this.kills = kills;
        updateFieldInDb("kills", kills);
    }

    public Integer getDeaths() { return deaths; }
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
        updateFieldInDb("deaths", deaths);
    }

    public Integer getChestopened() { return chestOpened; }
    public void setBedbreak(Integer chestOpened) {
        this.chestOpened = chestOpened;
        updateFieldInDb("chestOpened", chestOpened);
    }

    public Integer getCurrentWinStreak() { return currentwinstreak; }
    public void setCurrentwinstreak(Integer currentwinstreak) {
        this.currentwinstreak = currentwinstreak;
        updateFieldInDb("currentwinstreak", currentwinstreak);
    }

    public Integer getBestWinStreak() { return bestwinstreak; }
    public void setBestwinstreak(Integer bestwinstreak) {
        this.bestwinstreak = bestwinstreak;
        updateFieldInDb("bestwinstreak", bestwinstreak);
    }

    private void updateFieldInDb(String fieldName, Object value) {
        Mongo.collec.updateOne(eq("Joueur", playerStats.getString("Joueur")), Updates.set(fieldName, value));
    }
}
