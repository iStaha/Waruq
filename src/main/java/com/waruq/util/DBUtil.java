package com.waruq.util;

import java.sql.*;

public class DBUtil {

	
	public static void closeStatment(Statement s) {
		try {
			 if(s!=null) {
				 s.close();
			 }
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void closePreparedStatment(Statement ps) {
		try {
			 if(ps!=null) {
				 ps.close();
			 }
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			 if(rs!=null) {
				 rs.close();
			 }
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
}
