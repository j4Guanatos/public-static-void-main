package com.j4gdl.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Chapter {
	
	@Id
	private String id;
	
	private String name;
	
	private List<String> content;
	
	private boolean published;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}
	
	

}
