package com.j4gdl.ee.repository;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class FactoryMongoConnection {
	
	private final static FactoryMongoConnection instance = new FactoryMongoConnection();
	private final Morphia morphia = new Morphia();
	private final Datastore datastore;
	
	private FactoryMongoConnection(){
		morphia.mapPackage("com.j4gdl.ee.model");
		datastore = morphia.createDatastore(new MongoClient(), "j4gdl");
		datastore.ensureIndexes();
	}
	
	public static Datastore getDataSotre(){
		return instance.datastore;
	}

}
