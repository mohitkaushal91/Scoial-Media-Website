package com.project.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.project.models.User;

public class UserDBUtil {

	private DataSource datasource;

	public UserDBUtil(DataSource datasource) {
		this.datasource = datasource;
	}

	public void uploaddata(User user) throws SQLException {

		System.out.println(user.getName());
		System.out.println(user.getEmail());
		System.out.println(user.getPass());

		System.out.println("inseert    data for username");

		Connection conn = null;

		Statement smt = null;
		ResultSet res = null;

		try {
			conn = this.datasource.getConnection();
			
			String sql = String.format("INSERT INTO user values('%s','%s','%s','%s','%s')", user.getName(),
					user.getName(), user.getEmail(), user.getPass(), user.getName());
			
			smt = conn.createStatement();
			System.out.println("finally uploaded");

			smt.executeUpdate(sql);
		} finally {
			close(conn, smt, res);
		}

	}

	public User getUserDetails(String email) throws SQLException {

		Connection conn = null;

		Statement smt = null;
		ResultSet res = null;
		try {

			conn = this.datasource.getConnection();
			String sql = "Select * from user WHERE email=?";
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setString(1, email);
			res = pmt.executeQuery();

			res.next();
			User dbtemp = new User(res.getString(3), res.getString(2), res.getString(4));
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
