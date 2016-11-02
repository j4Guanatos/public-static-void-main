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
import org.springframework.web.bind.annotation.RestController;

import com.j4gdl.model.Content;
import com.j4gdl.respositories.ContentRepository;

@RestController
@RequestMapping("/contents")
public class Contents {
	
	@Inject
	ContentRepository contentRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Content> getContents(Pageable pageable){
		
		Page<Content> page = contentRepository.findAll(pageable);
		
		return page.getContent();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public Content getContent(@PathVariable String id){
		
		return contentRepository.findOne(id);
		
	}
	
	@RequestMapping(value="", method= RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public Content saveContent(@RequestBody Content content){
		
		return contentRepository.save(content);
		
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE)
	public Content deleteContent(@PathVariable("id") String id){
		
		Content content = contentRepository.findOne(id);
		contentRepository.delete(content);
		return content;
		
	}


}
