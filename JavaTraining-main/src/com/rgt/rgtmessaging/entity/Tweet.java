package com.rgt.rgtmessaging.entity;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

	private String id;

	private String content;

	private String author;

	private long timestamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public Tweet(String id, String content, String author, long timestamp) {
		super();
		this.id = id;
		this.content = content;
		this.author = author;
		this.timestamp = System.currentTimeMillis();
	}

	public Tweet(String id, String content, String author) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.timestamp = System.currentTimeMillis();
	}


}
