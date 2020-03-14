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
			
			while(res.next()) {
				int postid = res.getInt(1);
				String email = res.getString(2);
				String content = res.getString(3);
				Timestamp date = res.getTimestamp(5);
				
				user.setPosts(new Post(postid, email,content,date));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void DoGetOnlyUserPosts(User user) throws SQLException {
		
		try {
			
			String useremail = user.getEmail();
			
			System.out.println("this is post db running for specific email");
			System.out.println(user.getEmail());
			conn=this.datasource.getConnection();
			String sql="Select * from posts WHERE email=?";
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setString(1, useremail);
			res = pmt.executeQuery();
			
			System.out.println(res);
			
			System.out.println("just before while loop");
			
			while(res.next()) {
				
				System.out.println("while loop running to store data");
				int postid = res.getInt(1);
				String email = res.getString(2);
				String content = res.getString(3);
				Timestamp date = res.getTimestamp(5);
				
				System.out.println(email);
				
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
			
			System.out.println(postid);
			
			System.out.println("this is post db running to delete post");
			
			conn=this.datasource.getConnection();
			String sql="Delete from posts WHERE postID=?";
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setInt(1, postid);
			pmt.executeUpdate();
			
			System.out.println("post deleted");
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	/*
	public ArrayList<User> getPostDetails(String email) throws SQLException {

		ArrayList<User> useremployees = new ArrayList<>();

		Connection conn = null;
		Statement smt = null;
		ResultSet res = null;
		try {

			conn = this.datasource.getConnection();
			String sql = "Select * from posts WHERE email=?";
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setString(1, email);
			res = pmt.executeQuery();

			User user = new User();

			res.next();
			{

				user.setUserId(res.getInt(1));  // int id
				user.setPost(res.getString(2));   // email
				user.setName(res.getString(3));   // name
				
//				user.setUserId(res.getInt(3));

				useremployees.add(user);
				System.out.println("from db " + user.getUserId() + " 2 - " + user.getPost() + " 3 - " + user.getName());
			}
*/
			/*
			 * System.out.println(res.getString(1)); //email
			 * 
			 * System.out.println(res.getString(2)); // post
			 * 
			 * System.out.println(res.getInt(4));
			 * 
			 * 
			 * User dbtemp = new User(res.getString(3), res.getString(2), res.getInt(4));
			 * System.out.println(res.getString(3)); System.out.println(res.getString(2));
			 * System.out.println(res.getString(4));
			 */
/*
			return useremployees;

		}

		finally {
			close(conn, smt, res);
		}
	}

	public void getuserinfodatabase(String name) throws SQLException {
		Connection conn;
		Statement smt;
		ResultSet res;

		conn = this.datasource.getConnection();
		String sql = "Select name from users where name=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, name);
		res = pre.executeQuery();

		res.next();
		System.out.println(res.getString(1));

	}*/

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
