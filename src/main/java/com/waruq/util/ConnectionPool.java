package com.waruq.util;


import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
	
	private static ConnectionPool  pool = null;
	private static DataSource  dataSource = null;
	
	
	
	private ConnectionPool() {
		try {
			Context ic = new InitialContext();
			Context context  = (Context)ic.lookup("java:/comp/env");
			dataSource = (DataSource)context.lookup("jdbc/testdb");
		}
		catch(NamingException e) {
			System.out.println(e);
		}
	}
	
	public static synchronized ConnectionPool getInstance() {
		if(pool == null) {
			pool = new ConnectionPool();
		}
		return pool;
	}
	
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch(SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void freeConnection(Connection c) {
		try {
			c.close();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	
}
