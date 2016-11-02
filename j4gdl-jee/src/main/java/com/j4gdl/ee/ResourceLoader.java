package com.j4gdl.ee;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.j4gdl.ee.repository.ChapterRepository;
import com.j4gdl.ee.repository.FackeRepositoryImpl;
import com.j4gdl.ee.repository.FakeRepository;

public class ResourceLoader extends ResourceConfig{
	
	public ResourceLoader(){
		packages(true,"com.j4gdl.ee.endpoint");
		register(new AbstractBinder() {
			
			@Override
			protected void configure() {
				bind(FackeRepositoryImpl.class).to(FakeRepository.class);
				bind(ChapterRepository.class).to(ChapterRepository.class);
				
			}
		});
	}
 
    
}