package com.j4gdl.ee.endpoint;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.j4gdl.ee.repository.FakeRepository;
 
@Path("/helloworld")
public class HelloWorldRestResource {
	
	@Inject
	FakeRepository fakeRepository;
 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello World from Tomcat Embedded with Jersey!" + fakeRepository.fakeMethod();
    }
}
