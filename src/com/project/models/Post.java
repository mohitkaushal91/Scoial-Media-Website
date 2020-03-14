package com.project.models;

import java.sql.Timestamp;

public class Post {

	String email,content;
	Timestamp date;
	int postID;
	
	public Post()
	{
		
	}
	
	public Post(String email, String content) {
		// TODO Auto-generated constructor stub
		
		this.email=email;
		this.content=content;
	
	    
	}
	
	public Post(int postID, String email, String content,Timestamp date) {
		// TODO Auto-generated constructor stub
		this.postID = postID;
		this.email=email;
		this.content=content;
		this.date=date;
	    
	}

	public int getPostId() {
		return this.postID;
	}

	public void setPostId(int postID) {
		this.postID = postID;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return this.content;
	}

	public void setName(String content) {
		this.content = content;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
