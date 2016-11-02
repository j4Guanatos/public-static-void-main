package com.j4gdl.ee.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.j4gdl.ee.model.Chapter;
import com.j4gdl.ee.repository.ChapterRepository;

@Path("/chapters")
public class Chapters {

	@Inject
	ChapterRepository chapterRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Chapter> getAllChapters(){
		return chapterRepository.findAll();
	}
}
