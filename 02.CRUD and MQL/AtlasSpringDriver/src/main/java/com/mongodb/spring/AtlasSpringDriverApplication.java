package com.mongodb.spring;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mongodb.MongoException;
import com.mongodb.MongoSocketReadTimeoutException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

@SpringBootApplication
public class AtlasSpringDriverApplication {
	
	private static MongoClient mongoClient;
	private String connectionString = "mongodb+srv://<<user>:<<password>>3@serverless.9g4ngex.mongodb.net";
	private String Database = "handson";
	private MongoDatabase handsonDB = null;
	
	private MongoClient getMongoClient(String connectionString) throws RuntimeException {
		if (mongoClient == null) {
			
			try {
				mongoClient = MongoClients.create(connectionString);
			} catch (MongoException ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			} catch (Exception ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			}
		}

		return mongoClient;
	}
	
	private MongoDatabase getMongoDatabase() throws RuntimeException {
		getMongoClient(connectionString);
		if (handsonDB == null) {
			
			try {
				handsonDB = mongoClient.getDatabase(Database);
			} catch (MongoException ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			} catch (Exception ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			}
		}

		return handsonDB;
	}
	
	public void insertNewUser()
	{
		try{
			List<Document> _addresses = new ArrayList<Document> ();
			_addresses.add(new Document("type", "office").append("address","서울시 강남구 삼성동").append("detail", "코엑스 6"));
			_addresses.add(new Document("type", "home").append("address","서울시 강남구 역삼동").append("detail", "역삼 한국 아파트 101동 101호"));
			
			List<String> _hobbies = new ArrayList<String> ();
			_hobbies.add("Martial arts");
			
			Document _user = new Document();
			_user.append("ssn", "123-456-7890")
			.append("name", "Gildong Hong")
			.append("email", "gildong@email.com")
			.append("DateOfBirth", "25th Dec")
			.append("Hobbies", _hobbies)
			.append("Addresses", _addresses);
						
			MongoCollection<Document> _collection = getMongoDatabase().getCollection("Users");
			
			InsertOneResult rs = _collection.insertOne(_user);
			
			System.out.println("Insert Result : " + rs);
			
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	System.out.println("Timeout Insert Document ");
	    }
	    catch(Exception e)
	    {
	    	System.out.println("During Insert - Exception: "+e.toString());
	    }
	}
	
	public void updateUserHobby(String ssn, String hobby)
	{
		try{
			Document query = new Document().append("ssn",  ssn);
			
			Bson updates = Updates.combine(
                    Updates.push("Hobbies", hobby));
						
			MongoCollection<Document> _collection = getMongoDatabase().getCollection("Users");
			UpdateResult result = _collection.updateOne(query, updates);
			
			System.out.println("Update Result : " + result);
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	System.out.println("Timeout Update Document ");
	    }
	    catch(Exception e)
	    {
	    	System.out.println("During Update - Exception: "+e.toString());
	    }
	}
	
	
	public void updateUserAddresses(String ssn, String type, String zipcode)
	{
		try{
			Document query = new Document().append("ssn",  ssn).append("Addresses.type",  type);
			
			Bson updates = Updates.combine(
                    Updates.set("Addresses.$.Zip", zipcode)  );
						
			MongoCollection<Document> _collection = getMongoDatabase().getCollection("Users");
			UpdateResult result = _collection.updateOne(query, updates);
			
			System.out.println("Update Result : " + result);
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	System.out.println("Timeout Update Document ");
	    }
	    catch(Exception e)
	    {
	    	System.out.println("During Update - Exception: "+e.toString());
	    }
	}
	
	public void findUsers(String ssn)
	{
		try{
			Bson filter = eq("ssn", ssn);
			
			List<Document> iterDoc = getMongoDatabase().getCollection("Users").find(filter).projection(fields(excludeId(),include("ssn","name","email"))).into(new ArrayList<Document>());
			//System.out.println(""+iterDoc.first().size());
			
			for (Document user : iterDoc) {
				System.out.println("Success Find Document : "+user.toJson());
	            //System.out.println("Success Find:" + customer.toJson());
	        }
			
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	System.out.println("Timeout Insert Document ");
	    }
	    catch(Exception e)
	    {
	    	System.out.println("During Insert - Exception: "+e.toString());
	    }
	}
	
	public void deleteUser(String ssn)
	{
		try{
			Bson filter = eq("ssn", ssn);
			DeleteResult rs =  getMongoDatabase().getCollection("Users").deleteOne(filter);
			
			System.out.println("Delete Result : " + rs);
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	System.out.println("Timeout Insert Document ");
	    }
	    catch(Exception e)
	    {
	    	System.out.println("During Insert - Exception: "+e.toString());
	    }
	}

	public static void main(String[] args) {
		SpringApplication.run(AtlasSpringDriverApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			//insertNewUser();
			//findUsers("123-456-7890");
			//updateUserHobby("123-456-7890","Reading");
			//updateUserAddresses("123-456-7890","home", "06230");
			deleteUser("123-456-7890");
			
		};
	}

}
