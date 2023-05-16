package fun.slowfeew.api.Database;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Mongo {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public static com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    public static MongoDatabase db = mongoClient.getDatabase("Slow");
    public static MongoCollection<Document> collec = db.getCollection("General");

    public void insertDocument(Document doc) {
        this.collection.insertOne(doc);
    }

    public Document findDocument(String id) {
        return this.collection.find(eq("_id", id)).first();
    }

    public void updateDocument(String id, Document newDoc) {
        this.collection.replaceOne(eq("_id", id), newDoc);
    }

    public void deleteDocument(String id) {
        this.collection.deleteOne(eq("_id", id));
    }
}
