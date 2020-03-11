package com.project.models;

import java.sql.SQLException;
import java.util.ArrayList;
import com.project.db.PostDBUtil;
import com.project.db.UserDBUtil;

public class User {

	String email;
	String name;
	String pass;
	ArrayList<Post> posts  =  new ArrayList<>();
	
	ArrayList<Post> userPosts  =  new ArrayList<>();
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
	
	public User(String email,String name,int userID) {
		this.email=email;
		this.name=name;
		this.userID=userID;
	}
	
	public User(int userId, String email,String post) {
		this.userID = userId;
		this.email = email;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	
	public User(String inputtext) {
	this.inputtext=inputtext;	
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
		return this.pass;			
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean getMatch()
	{
		return this.matched;
	}
	public void setMatch(boolean match)
	{
		this.matched = match;
	}
	
	public ArrayList<Post> getPosts()
	{
		return this.posts;
	}
	
	public String getPost()
	{
		return this.post;
	}
	public void setPost(String post)
	{
		this.post = post;
	}
	
	
	public void setPosts(Post post)
	{
		this.posts.add(post);
	}
	
	public void setUserPosts(Post post)
	{
		this.userPosts.add(post);
	}
	public ArrayList<Post> getUserPosts()
	{
		return this.userPosts;
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
				this.email = dtemp.getEmail();
				
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
	
	public void post(String post ,PostDBUtil postdb)
	{
		System.out.println("user post");
		try {
			
			System.out.println(this.email);
System.out.println(post);
			postdb.DoPost(new Post(this.email,post));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
	public void DisplayAllPosts(PostDBUtil postdb) {
		
		try {
			postdb.DoGet(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void GetUserPost(PostDBUtil postdb) {
		
		try {
			postdb.DoGetOnlyUserPosts(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
/*
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
