package com.j4gdl.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.j4gdl.model.Chapter;

public interface ChapterRepository extends MongoRepository<Chapter, String>{

}
