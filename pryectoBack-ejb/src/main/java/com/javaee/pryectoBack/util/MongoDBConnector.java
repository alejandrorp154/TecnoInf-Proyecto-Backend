package com.javaee.pryectoBack.util;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnector {
	
	private static final String MONGO_DB_URI = "mongodb+srv://soportetravelpack:proyecto2021@travelpack.iwxkh.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
	private static final String TRAVELPACK_DATABASE_MONGO = "Travelpack";
	
	private MongoClient client = null;
	private MongoDatabase dataBase = null;
	
	public MongoDBConnector() {
		this.client = MongoClients.create(MONGO_DB_URI);
		this.dataBase = this.client.getDatabase(TRAVELPACK_DATABASE_MONGO);
	}	
	
	MongoCollection<Document> getCollection(String collectionName){
		if (this.client != null && this.dataBase != null) {
			return this.dataBase.getCollection(collectionName);
		} else return null;
	}
}
