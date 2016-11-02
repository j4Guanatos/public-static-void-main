package com.j4gdl.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.j4gdl.model.Module;

public interface ModuleRepository extends MongoRepository<Module, String>{

}
