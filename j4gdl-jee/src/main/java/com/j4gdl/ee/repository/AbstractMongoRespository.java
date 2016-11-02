package com.j4gdl.ee.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;

public abstract class AbstractMongoRespository<ENTITY> {

	
	protected Datastore datastore = FactoryMongoConnection.getDataSotre();
	
	protected Class<ENTITY> clazz;
	
	@SuppressWarnings("unchecked")
	public AbstractMongoRespository(){
		clazz = (Class<ENTITY>) ((ParameterizedType) getClass()  
	             .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	public List<ENTITY> findAll(){
		Query<ENTITY> query = datastore.createQuery(clazz);
		return query.asList();
	}
	
	public ENTITY findOne(String id){
		Query<ENTITY> query = datastore.createQuery(clazz);
		return query.field("_id").equal(id).get();
	}
	
	public ENTITY save(ENTITY entity){
		Key<ENTITY> persisted = datastore.save(entity);
		Query<ENTITY> query = datastore.createQuery(clazz);
		return query.field("_id").equal(persisted.getId()).get();
	}
	
}
