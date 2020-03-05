package com.project.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import com.project.models.User;


public class PostDBUtil {

	
	private DataSource datasource;
	
	public PostDBUtil(DataSource datasource) {
		this.datasource=datasource;
	}
	
	
	public void DoPost(User user) throws SQLException {

		Connection conn = null;

		Statement smt = null;
		ResultSet res = null;
		try {

			conn = this.datasource.getConnection();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now(); 
			   
			String sql = String.format("INSERT INTO posts values('%s','%s','%s','%s','%s')",1235, user.getEmail(), user.getPost(), null, dtf.format(now));

//			System.out.println(user.getEmail());
//
//			System.out.println(user.getPost());
			smt = conn.createStatement();
//		

			smt.executeUpdate(sql);
		}

		finally {
			close(conn, smt, res);
		}
	}
	
	
	public User getUserDetails(String email,String name,int date) throws SQLException {

		Connection conn = null;

		Statement smt = null;
		ResultSet res = null;
		try {

			conn = this.datasource.getConnection();
			String sql = "Select * from posts WHERE email=?, name=?, date=?";
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setString(1, email);
			pmt.setString(2, name);
			pmt.setInt(3, date);
			res = pmt.executeQuery();

			res.next();
//			User dbtemp = new User(res.getString(1), res.getString(2), res.getint(3));
			return dbtemp;
		}

		finally {
			close(conn, smt, res);
		}
	}

	private void close(Connection conn, Statement smt, ResultSet res) {
		try {
			if (res != null) {
				res.close();
			}
			if (smt != null) {
				smt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}
	
	
	
}
