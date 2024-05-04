package consulta;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {

	public static void main(String[] args) {
		  try {
	        	ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/");
	        	MongoClient mongoClient = MongoClients.create(connectionString);

	        	MongoDatabase database = mongoClient.getDatabase("ComunidadesProvinciasPoblaciones");
	            Bson command = new BsonDocument("ping", new BsonInt64(1));
	            database.runCommand(command);
	            System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
	        } catch (MongoException me) {
	            System.err.println(me);
	        }

	}

}
