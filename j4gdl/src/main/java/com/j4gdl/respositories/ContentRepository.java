package com.j4gdl.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.j4gdl.model.Content;

public interface ContentRepository extends MongoRepository<Content, String>{

}
