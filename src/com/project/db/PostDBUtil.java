package com.project.db;
import java.sql.Connection;

import javax.sql.DataSource;


public class PostDBUtil {

	
	private DataSource datasource;
	
	public PostDBUtil(DataSource datasource) {
		this.datasource=datasource;
	}
	
}
