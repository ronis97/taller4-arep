package edu.escuelaing.arep;

import spark.Request;
import spark.Response;

import static spark.Spark.*;
public class SparkWebServer
{
    private static MongoBDConnection mongoConnection = new MongoBDConnection();
    public static void main(String... args){
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        post("/message", (req,res) -> insertMessage(req,res));
        get("ronaldo", (req,res) -> "Ronaldo");

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    private static String insertMessage(spark.Request request, spark.Response response){
        mongoConnection.insertMessage(request.body());
        String content = getMessages(request,response);
        return content;
    }

    private static String getMessages(spark.Request request, spark.Response response){
        String content = mongoConnection.getData();
        return content;
    }
}
