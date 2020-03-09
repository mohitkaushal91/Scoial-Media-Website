package com.project.models;

import java.sql.SQLException;

import com.project.db.PostDBUtil;
import com.project.db.UserDBUtil;

public class User {

	String email;
	String name;
	String pass;
	String post;
	String inputtext;
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
	
	public User( String email, String name,int userId) {
		this.userID = userId;
		this.name=name;
		this.email = email;
	}
	public User(int userId, String email,String post) {
		this.userID = userId;
		this.post=post;
		this.email = email;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	
	public User(String inputtext) {
	this.inputtext=inputtext;	
	}
	
	public String getPost() {
		return post;
	}
	
	public void setPost(String post) {
		 this.post=post;
	}
	
	public int getUserId() {
		return userID;
	}
	public int setUserId(int userID) {
		this.userID=userID;
		return userID;
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
	
	/*public String getInputText() {
		return inputtext;
	}
	
	public  void setInputText(String inputtext) {
		this.inputtext=inputtext;
	}*/
	
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
	
	
/*	
public ArrayList<User> DisplayDatabasePosts(PostDBUtil postdb) {
		
		try {
			return(postdb.getPostDetails(this.email));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

public void searchfriendindatabase(String inputtext) {
	
	PostDBUtil postdb=new PostDBUtil();
	
	try {
		postdb.getuserinfodatabase(this.getInputText());
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}
	
}*/
	
	
}
