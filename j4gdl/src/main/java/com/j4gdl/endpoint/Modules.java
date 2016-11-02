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

import com.j4gdl.model.Module;
import com.j4gdl.respositories.ModuleRepository;

@RestController
@RequestMapping("/modules")
public class Modules {
	
	@Inject
	ModuleRepository moduleRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Module> getModules(Pageable pageable){
		
		Page<Module> page = moduleRepository.findAll(pageable);
		
		return page.getContent();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public Module getModule(@PathVariable String id){
		
		return moduleRepository.findOne(id);
		
	}
	
	@RequestMapping(value="", method= RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public Module saveModule(@RequestBody Module module){
		
		return moduleRepository.save(module);
		
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE)
	public Module deleteModule(@PathVariable("id") String id){
		
		Module module = moduleRepository.findOne(id);
		moduleRepository.delete(module);
		return module;
		
	}

}
