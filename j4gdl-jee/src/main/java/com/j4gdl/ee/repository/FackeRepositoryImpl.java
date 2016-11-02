package com.j4gdl.ee.repository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FackeRepositoryImpl implements FakeRepository{

	@Override
	public String fakeMethod() {
		// TODO Auto-generated method stub
		return "Hello from CDI embedded";
	}

}
