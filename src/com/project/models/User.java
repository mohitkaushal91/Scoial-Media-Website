package com.project.models;

import java.sql.SQLException;

import com.project.db.PostDBUtil;
import com.project.db.UserDBUtil;

public class User {

	String email;
	String name;
	String pass;
	String post;
	int userID;
	Boolean matched;
	public User(String email, String name, String pass) {

		this.email = email;
		this.name = name;
		this.pass = pass;
	}
	
	public User(String email, String pass) {
		this.email = email;
		this.pass = pass;
	}
	
	public User(int userId, String email, String post) {
		this.userID = userId;
		this.post=post;
		this.email = email;
	}
	
	public String getPost() {
		return post;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;			
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean getMatch()
	{
		return matched;
	}
	public void setMatch(boolean match)
	{
		this.matched = match;
	}
	
	
	public void register(UserDBUtil userdb) {
		
		try {
			userdb.uploaddata(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void login(UserDBUtil userdb) {
		try {
			User dtemp = userdb.getUserDetails(this.email);
			
			if(this.pass.equals(dtemp.getPass()))
			{
				this.name = dtemp.getName();
				this.setMatch(true);
				
				
			}	
			else
			{
				this.setMatch(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void post(PostDBUtil postdb)
	{
		try {
			postdb.DoPost(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
