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

import consulta.entidades.Provincia;



public class ControladorProvincia {
	

	private static ControladorProvincia instance = null;
	
	public static ControladorProvincia getInstance() {
		if (instance == null) {
			instance = new ControladorProvincia();
		}
		return instance;
	}


	  public static List<Provincia> getAllProvincia(MongoCollection<Document> col) {
	        System.out.println("Obteniendo todas las ccaa de la colección");
	 
	        // Performing a read operation on the collection.
	        FindIterable<Document> fi = col.find();
	        MongoCursor<Document> cursor = fi.iterator();

	        List<Provincia> allProvincia = new ArrayList<Provincia>();
	        try {
	            while(cursor.hasNext()) {
	            	allProvincia.add(documentToProvincia(cursor.next()));
	            }
	        } finally {
	            cursor.close();
	        }
	        
	        return allProvincia;
	    }
	    private static Provincia documentToProvincia(Document doc) {
	    	Provincia provincia = new Provincia();
	    	provincia.setParentCode(doc.getString("parent_code"));
	    	provincia.setCode(doc.getString("code"));
	    	provincia.setLabel(doc.getString("label"));
	    	return provincia;
	    }
	    /**
	     * 
	     */
	    public List<Provincia> allProvincias() {
	    	 // Mongodb inicializando parámetros.
	        int port_no = 27017;
	        String host_name = "localhost", db_name = "ComunidadesProvinciasPoblaciones", 
	        		db_coll_name = "provincias";
	 
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
	        List<Provincia> provincias = getAllProvincia(coll); System.out.println("\n");
	        for(Provincia p : provincias) {
	        	System.out.println("Code: " + p.getCode() + " - Label: " + p.getLabel());
	        }
	        return provincias;
	    }
	    /**
	     * 
	     * @param col
	     */
	    public static void updateDocument (Provincia provincia) {
	        try {
	        	 // Obtener la colección de MongoDB
	            MongoClient mongoClient = new MongoClient("localhost", 27017);
	            MongoDatabase database = mongoClient.getDatabase("ComunidadesProvinciasPoblaciones");
	            MongoCollection<Document> col = database.getCollection("provincias");
	        	//crea la query para encontrar el documento
	        	Document query = new Document().append("code", provincia.getCode());
	        	//Crea la actualizacion
	        	Bson update = Updates.combine(Updates.set("label", provincia.getLabel()));
	        	//Ejecuta la actualizacion
	        	UpdateResult result = col.updateOne(query, update);
	        	
	        	System.out.println("Modificados: " + result.getModifiedCount());
	        }
	        catch (Exception ex) {
	        	ex.printStackTrace();
	        }
	        
	    }
}
