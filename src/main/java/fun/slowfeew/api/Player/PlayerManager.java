package fun.slowfeew.api.Player;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fun.slowfeew.api.Database.Mongo;
import fun.slowfeew.api.SlowAPI;
import org.bson.Document;

import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.pull;
import static com.mongodb.client.model.Updates.push;

public class PlayerManager {

    public static boolean createPlayer(String uuid, String joueur) {
        if(ifPlayerExist(uuid)) {
            return false;  // Le joueur existe déjà, ne tentez pas de le créer
        }

        Document playerAccount = new Document()
                .append("UUID", uuid.toString())
                .append("UUID", joueur)
                .append("rang", "default")
                .append("freeMoney1", 0)
                .append("uuid", "null");
        Mongo.collec.insertOne(playerAccount);
        return true;  // Le joueur a été créé avec succès
    }

    public static String getPseudo(String uuid) {
        Document player = Mongo.collec.find(eq("UUID", uuid)).first();
        return player != null ? player.getString("UUID") : "null";
    }

    public static boolean ifPlayerExist(String uuid) {
        Document player = Mongo.collec.find(eq("UUID", uuid)).first();
        return player != null;
    }

    public static boolean ifPlayerIsMod(String uuid) {
        Document player = Mongo.collec.find(eq("UUID", uuid)).first();
        return player != null ? player.getBoolean("Mod") : false;
    }

}
