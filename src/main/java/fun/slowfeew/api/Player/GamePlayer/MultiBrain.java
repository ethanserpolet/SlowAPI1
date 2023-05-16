package fun.slowfeew.api.Player.GamePlayer;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import fun.slowfeew.api.SlowAPI;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class MultiBrain {

    private MongoCollection<Document> collection;
    private Document playerStats;

    private Integer elo;
    private Integer topelo;
    private Integer win;
    private Integer looses;
    private Integer playedgames;
    private Integer kills;
    private Integer deaths;
    private Integer bedBroken;
    private Integer bedLost;
    private Integer currentWinStreak;
    private Integer bestwinstreak;

    public MultiBrain(MongoCollection<Document> collection, String joueur) {
        this.collection = collection;
        Document player = this.collection.find(eq("Joueur", joueur)).first();
        if (player != null) {
            this.playerStats = player;
            this.elo = player.getInteger("elo");
            this.topelo = player.getInteger("topelo");
            this.win = player.getInteger("victoire");
            this.looses = player.getInteger("defaites");
            this.playedgames = player.getInteger("playedgames");
            this.kills = player.getInteger("kills");
            this.deaths = player.getInteger("deaths");
            this.bedBroken = player.getInteger("bedbreak");
            this.bedLost = player.getInteger("bedlost");
            this.currentWinStreak = player.getInteger("currentwinstreak");
            this.bestwinstreak = player.getInteger("bestwinstreak");
        } else {
            throw new IllegalArgumentException("Joueur " + joueur + " n'a pas été trouvé dans la collection.");
        }
    }

    public static MultiBrain getInfos(String pseudoJoueur) {
        MongoCollection<Document> collection = SlowAPI.getInstance().getMongoDB().getCollection();
        return new MultiBrain(collection, pseudoJoueur);
    }

    // Getters and setters
    // ...

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
        updateFieldInDb("defaites", looses);
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

    public Integer getBedBroken() { return bedBroken; }
    public void setBedbreak(Integer bedBroken) {
        this.bedBroken = bedBroken;
        updateFieldInDb("bedBroken", bedBroken);
    }

    public Integer getBedLost() { return bedLost; }
    public void setBedLost(Integer bedLost) {
        this.bedLost = bedLost;
        updateFieldInDb("bedlost", bedLost);
    }

    public Integer getCurrentWinStreak() { return currentWinStreak; }
    public void setCurrentwinstreak(Integer currentWinStreak) {
        this.currentWinStreak = currentWinStreak;
        updateFieldInDb("currentWinStreak", currentWinStreak);
    }

    public Integer getBestWinStreak() { return bestwinstreak; }
    public void setBestwinstreak(Integer bestwinstreak) {
        this.bestwinstreak = bestwinstreak;
        updateFieldInDb("bestwinstreak", bestwinstreak);
    }

    // Helper method to update a field in the database
    private void updateFieldInDb(String fieldName, Object value) {
        collection.updateOne(eq("Joueur", playerStats.getString("Joueur")), Updates.set(fieldName, value));
    }
}
