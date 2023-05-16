package fun.slowfeew.api.Player;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.pull;
import static com.mongodb.client.model.Updates.push;

public class PlayerFriends {

    private static MongoCollection<Document> collection;

    public PlayerFriends(MongoCollection<Document> collection) {
        PlayerFriends.collection = collection;
    }

    public static List<String> getFriends(String joueur) {
        Document player = collection.find(eq("Joueur", joueur)).first();
        if (player != null) {
            return (List<String>) player.get("Friends");
        } else {
            return null;
        }
    }

    public static void addFriend(String joueur, String friend) {
        collection.updateOne(eq("Joueur", joueur), push("Friends", friend));
    }

    public static void removeFriend(String joueur, String friend) {
        collection.updateOne(eq("Joueur", joueur), pull("Friends", friend));
    }

}
