package edu.escuelaing.arep;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Date;


public class MongoBDConnection {
    private MongoClientURI uri;
    private MongoClient client;
    private MongoDatabase database;

    public MongoBDConnection() {
    try {

        ConnectionString connectionString = new ConnectionString("mongodb://ronaldohenao1:970805ERhv@ac-nyfgawm-shard-00-00.5xpoez1.mongodb.net:27017,ac-nyfgawm-shard-00-01.5xpoez1.mongodb.net:27017,ac-nyfgawm-shard-00-02.5xpoez1.mongodb.net:27017/?ssl=true&replicaSet=atlas-ktarhu-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        com.mongodb.client.MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("messagestest");

    }catch (Exception e){

    }

    }

    public void insertMessage(String message){
        MongoCollection<Document> collection = database.getCollection("messages");
        Document document = new Document();
        System.out.println(message);
        document.append("mensaje",message);
        document.append("fecha", new Date().toString());
        collection.insertOne(document);
    }

    public String getData(){
        MongoCollection<Document> collection = database.getCollection("messages");
        String message = "";
        FindIterable<Document> iterable = collection.find();
        for (Document document: iterable) {
            message += "<tr><td>" 
            + document.get("mensaje").toString() 
            + "<p>"
            + "</td><td>" + document.get("fecha").toString() 
            + "<p>"
            + "</td></tr>";
        }
        //System.out.println(message);
        return message;
    }
}
