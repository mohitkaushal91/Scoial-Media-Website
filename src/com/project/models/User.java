package com.project.models;

import java.sql.SQLException;

import com.project.db.UserDBUtil;

public class User {

	String email;
	String name;
	String pass;
	public User(String email, String name, String pass) {

		this.email = email;
		this.name = name;
		this.pass = pass;
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
	
	
	public void register(UserDBUtil userdb) {
		
		try {
			userdb.uploaddata(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
