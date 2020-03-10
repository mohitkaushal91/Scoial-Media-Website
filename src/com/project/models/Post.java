package com.project.models;

import java.sql.Timestamp;

public class Post {

	String email,content;
	Timestamp date;
	
	public Post()
	{
		
	}
	
	public Post(String email, String content) {
		// TODO Auto-generated constructor stub
		
		this.email=email;
		this.content=content;
	
	    
	}
	
	public Post(String email, String content,Timestamp date) {
		// TODO Auto-generated constructor stub
		
		this.email=email;
		this.content=content;
		this.date=date;
	    
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
