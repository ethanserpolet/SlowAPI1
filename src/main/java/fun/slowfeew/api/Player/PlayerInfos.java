package fun.slowfeew.api.Player;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import fun.slowfeew.api.Database.Mongo;
import fun.slowfeew.api.SlowAPI;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

   /*
    MongoCollection<Document> collection = ... // Obtenez la collection MongoDB ici
    String UUID = ... // Obtenez le pseudo du UUID ici
    PlayerInfos playerInfos = new PlayerInfos(collection, UUID);
    */

public class PlayerInfos {

    private MongoCollection<Document> collection;
    private Document playerInfo;

    private String rank;
    private Double freemoney1;
    private String uuid;
    private String prefix;
    private Double freemoney2;
    private String regip;
    private String playtime;
    private String regdate;
    private Double paidmoney;
    private String played;
    private Integer onlines;
    private String lastlogin;

    public PlayerInfos(MongoCollection<Document> collection, String UUID) {
        Document player = Mongo.collec.find(eq("UUID", UUID)).first();
        if (player != null) {
            this.playerInfo = player;
            this.rank = player.getString("rang");
            this.freemoney1 = player.getDouble("freeMoney1");
            this.uuid = player.getString("UUID");
            this.prefix = player.getString("prefix");
            this.freemoney2 = player.getDouble("freeMoney2");
            this.regip = player.getString("registerIP");
            this.playtime = player.getString("playedTime");
            this.regdate = player.getString("registerDate");
            this.paidmoney = player.getDouble("paidMoney");
            this.played = player.getString("playedGames");
            this.onlines = player.getInteger("isOnline");
            this.lastlogin = player.getString("lastLogin");
        } else {
            throw new IllegalArgumentException("UUID " + UUID + " n'a pas été trouvé dans la collection.");
        }
    }

    public static PlayerInfos getInfos(String UUID) {
        return new PlayerInfos(Mongo.collec, UUID);
    }

    // Getters pour accéder aux informations du UUID
    public String getRank() { return rank; }
    public void setRank(String rank) {
        this.rank = rank;
        updateFieldInDb("rang", rank);
    }

    public Double getFreemoney1() { return freemoney1; }
    public void setFreemoney1(Double freemoney1) {
        this.freemoney1 = freemoney1;
        updateFieldInDb("freeMoney1", freemoney1);
    }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) {
        this.uuid = uuid;
        updateFieldInDb("uuid", uuid);
    }

    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        updateFieldInDb("prefix", prefix);
    }

    public Double getFreemoney2() { return freemoney2; }
    public void setFreemoney2(Double freemoney2) {
        this.freemoney2 = freemoney2;
        updateFieldInDb("freeMoney2", freemoney2);
    }

    public String getRegip() { return regip; }
    public void setRegip(String regip) {
        this.regip = regip;
        updateFieldInDb("registerIP", regip);
    }

    public String getPlaytime() { return playtime; }
    public void setPlaytime(String playtime) {
        this.playtime = playtime;
        updateFieldInDb("playedTime", playtime);
    }

    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) {
        this.regdate = regdate;
        updateFieldInDb("registerDate", regdate);
    }

    public Double getPaidmoney() { return paidmoney; }
    public void setPaidmoney(Double paidmoney) {
        this.paidmoney = paidmoney;
        updateFieldInDb("paidMoney", paidmoney);
    }

    public String getPlayed() { return played; }
    public void setPlayed(String played) {
        this.played = played;
        updateFieldInDb("playedGames", played);
    }

    public Integer getOnlines() { return onlines; }
    public void setOnlines(Integer onlines) {
        this.onlines = onlines;
        updateFieldInDb("isOnline", onlines);
    }

    public String getLastlogin() { return lastlogin; }
    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
        updateFieldInDb("lastLogin", lastlogin);
    }

    // Méthode d'aide pour mettre à jour un champ dans la base de données
    private void updateFieldInDb(String fieldName, Object value) {
        Mongo.collec.updateOne(eq("UUID", playerInfo.getString("UUID")), Updates.set(fieldName, value));
    }
}
