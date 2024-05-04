package consulta.controladores;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import consulta.entidades.Ccaa;
import consulta.entidades.Provincia;



public class ControladorCcaa {

	private static ControladorCcaa instance = null;
	
	public static ControladorCcaa getInstance() {
		if (instance == null) {
		instance = new ControladorCcaa();	
		}
		return instance;
	}
	
	  public static List<Ccaa> getAllCcaa(MongoCollection<Document> col) {
	        System.out.println("Obteniendo todas las ccaa de la colección");
	 
	        // Performing a read operation on the collection.
	        FindIterable<Document> fi = col.find();
	        MongoCursor<Document> cursor = fi.iterator();

	        List<Ccaa> allCcaa = new ArrayList<Ccaa>();
	        try {
	            while(cursor.hasNext()) {
	            	allCcaa.add(documentToCcaa(cursor.next()));
	            }
	        } finally {
	            cursor.close();
	        }
	        
	        return allCcaa;
	    }
	    private static Ccaa documentToCcaa(Document doc) {
	    	Ccaa ccaa = new Ccaa();
	    	ccaa.setParentCode(doc.getString("parent_code"));
	    	ccaa.setCode(doc.getString("code"));
	    	ccaa.setLabel(doc.getString("label"));
	    	return ccaa;
	    }
	    /**
	     * 
	     */
	    public List<Ccaa> allCcaa() {
	    	 // Mongodb inicializando parámetros.
	        int port_no = 27017;
	        String host_name = "localhost", db_name = "ComunidadesProvinciasPoblaciones", 
	        		db_coll_name = "ccaa";
	 
	        // Mongodb creando la cadena de conexión.
	        String client_url = "mongodb://" + host_name + ":" + port_no + "/" + db_name;
	        MongoClientURI uri = new MongoClientURI(client_url);
	 
	        // Conectando y obteniendo un cliente.
	        MongoClient mongo_client = new MongoClient(uri);
	 
	        // Obteniendo un enlace a la base de datos.
	        MongoDatabase db = mongo_client.getDatabase(db_name);
	 
	        // Obteniendo la colección de la base de datos
	        MongoCollection<Document> coll = db.getCollection(db_coll_name);
	 
	        // Obteniendo todos los documentos de la colección.
//	      getAllDocuments(coll); System.out.println("\n");

	        // Obteniendo todos los documentos de la colección.
	        List<Ccaa> ccaa = getAllCcaa(coll); System.out.println("\n");
	        for(Ccaa c : ccaa) {
	        	System.out.println("Code: " + c.getCode() + " - Label: " + c.getLabel());
	        }
	        return ccaa;
	    }
	    
	    /**
	     * 
	     * @param col
	     */
	    public static void updateDocument (Ccaa ccaa) {
	        try {
	        	 // Obtener la colección de MongoDB
	            MongoClient mongoClient = new MongoClient("localhost", 27017);
	            MongoDatabase database = mongoClient.getDatabase("ComunidadesProvinciasPoblaciones");
	            MongoCollection<Document> col = database.getCollection("ccaa");
	        	//crea la query para encontrar el documento
	        	Document query = new Document().append("code", ccaa.getCode());
	        	//Crea la actualizacion
	        	Bson update = Updates.combine(Updates.set("label", ccaa.getLabel()));
	        	//Ejecuta la actualizacion
	        	UpdateResult result = col.updateOne(query, update);
	        	
	        	System.out.println("Modificados: " + result.getModifiedCount());
	        }
	        catch (Exception ex) {
	        	ex.printStackTrace();
	        }
	        
	    }
}
