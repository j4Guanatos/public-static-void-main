package com.j4gdl.endpoint;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.j4gdl.model.Chapter;
import com.j4gdl.respositories.ChapterRepository;

@RestController
@RequestMapping("/chapters")
public class Chapters {
	
	@Inject
	ChapterRepository chapterRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Chapter> getChapters(Pageable pageable){
		
		Page<Chapter> page = chapterRepository.findAll(pageable);
		
		return page.getContent();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public Chapter getChapter(@PathVariable String id){
		
		return chapterRepository.findOne(id);
		
	}
	
	@RequestMapping(value="", method= RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public Chapter saveChapter(@RequestBody Chapter chapter){
		
		return chapterRepository.save(chapter);
		
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE)
	public Chapter deleteChapter(@PathVariable("id") String id){
		
		Chapter chapter = chapterRepository.findOne(id);
		chapterRepository.delete(chapter);
		return chapter;
		
	}
	

}
