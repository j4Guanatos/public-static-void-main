package com.j4gdl.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Module {
	
	@Id
	private String id;
	
	private String name;
	
	private String videoIntroUrl;
	
	private List<String> chapters;
		
	private String about;
	
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

	public String getVideoIntroUrl() {
		return videoIntroUrl;
	}

	public void setVideoIntroUrl(String videoIntroUrl) {
		this.videoIntroUrl = videoIntroUrl;
	}

	public List<String> getChapters() {
		return chapters;
	}

	public void setChapters(List<String> chapters) {
		this.chapters = chapters;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}
	
	

}
