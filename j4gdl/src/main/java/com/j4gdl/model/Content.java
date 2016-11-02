package com.j4gdl.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Content {

	@Id
	private String id;
	
	private String video;
	
	private List<String> markdownContent;
	
	private boolean published;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<String> getMarkdownContent() {
		return markdownContent;
	}

	public void setMarkdownContent(List<String> markdownContent) {
		this.markdownContent = markdownContent;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}
	
	
}
