package com.project.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;

import com.project.models.Post;
//import com.project.models.Post;
import com.project.models.User;

public class PostDBUtil {

	private DataSource datasource;
	Connection conn = null;
	Statement smt = null;
	ResultSet res = null;
	ResultSet result = null;
	ResultSet resultofuser = null;
	User userdb;

	public PostDBUtil() {

	}

	public PostDBUtil(DataSource datasource) {
		this.datasource = datasource;
	}

	public void DoPost(Post post) throws SQLException {

		System.out.println("start do post");
		
		int max = 99999, min = 1;
		Random rand = new Random();
		int randno = rand.nextInt((max - min) + 1) + min;

		Connection conn = null;
		Statement smt = null;
		ResultSet res = null;
		try {

			conn = this.datasource.getConnection();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			String sql = String.format("INSERT INTO posts values('%s','%s','%s','%s','%s')", randno, post.getEmail(),
					post.getContent(), null, dtf.format(now));

			System.out.println(post.getEmail());
			System.out.println(post.getContent());
			
			System.out.println("hello post done");
			smt = conn.createStatement();

			int r =  smt.executeUpdate(sql);		
			System.out.println(r);
		
		}

		finally {
			close(conn, smt, res);
		}
	}
	
	public void DoGet(User user) throws SQLException {
	
		try {
			conn=this.datasource.getConnection();
			String sql="Select * from posts";
			smt = conn.createStatement();
			res=smt.executeQuery(sql);
			
			String useremail = user.getEmail();
			
			while(res.next()) {
				int likes = 0;
				int postid = res.getInt(1);
				String email = res.getString(2);
				String content = res.getString(3);
				Timestamp date = res.getTimestamp(5);
				
				String sql1="Select COUNT(email) from likes WHERE postID=?";
				PreparedStatement pmt1 = conn.prepareStatement(sql1);
				pmt1.setInt(1, postid);
				result = pmt1.executeQuery();
				
				result.next();
				likes = result.getInt(1);
				
				String sql2="Select postID from likes WHERE email=?";
				PreparedStatement pmt2 = conn.prepareStatement(sql2);
				pmt2.setString(1, useremail);
				resultofuser = pmt2.executeQuery();
				
				while(resultofuser.next())
				{
					user.setLikedPostInSession(resultofuser.getInt(1));
				}
				
				user.setPosts(new Post(postid, email,content,date, likes));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void DoGetOnlyUserPosts(User user) throws SQLException {
		
		try {
			
			String useremail = user.getEmail();
			
			conn=this.datasource.getConnection();
			String sql="Select * from posts WHERE email=?";
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setString(1, useremail);
			res = pmt.executeQuery();
			
			while(res.next()) {
				
				int postid = res.getInt(1);
				String email = res.getString(2);
				String content = res.getString(3);
				Timestamp date = res.getTimestamp(5);
				
				user.setUserPosts(new Post(postid, email,content,date));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deletePost(User user) throws SQLException {
			
			try {
				
				int postid = user.getPostId();
				
				conn=this.datasource.getConnection();
				String sql="Delete from posts WHERE postID=?";
				PreparedStatement pmt = conn.prepareStatement(sql);
				pmt.setInt(1, postid);
				pmt.executeUpdate();
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}


	public void UpdateEditPost(User user) throws SQLException {
		
		int postid = user.getPostId();
		String neweditpost = user.getPost();
	
		Connection conn = null;
		Statement smt = null;
		ResultSet res = null;
		try {
	
			conn = this.datasource.getConnection();
			
			String sql = "update posts set content=? where postID=?";
		      PreparedStatement preparedStmt = conn.prepareStatement(sql);
		      preparedStmt.setString(1, neweditpost);
		      preparedStmt.setInt(2, postid);
		      
		      preparedStmt.executeUpdate();
		}
	
		finally {
			close(conn, smt, res);
		}
	}
	
public void insertLikes(User user) throws SQLException {
		
		int postid = user.getPostId();
		String email = user.getEmail();
		
		System.out.println(postid);
		System.out.println(email);
		
	
		Connection conn = null;
		Statement smt = null;
		ResultSet res = null;
		try {
	
			conn = this.datasource.getConnection();
			System.out.println("just before insert");
			
			String sql = String.format("INSERT INTO likes values('%d','%s')", postid, email);
			smt = conn.createStatement();
			int r =  smt.executeUpdate(sql);
			
			System.out.println("inserytion done");
		}
	
		finally {
			close(conn, smt, res);
		}
	}
	

public void deleteLikes(User user) throws SQLException {
	
	int postid = user.getPostId();
	String email = user.getEmail();

	Connection conn = null;
	Statement smt = null;
	ResultSet res = null;
	try {

		conn = this.datasource.getConnection();
		
		String sql="Delete from likes WHERE postID=? AND email=?";
		PreparedStatement pmt = conn.prepareStatement(sql);
		pmt.setInt(1, postid);
		pmt.setString(2, email);
		pmt.executeUpdate();
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
