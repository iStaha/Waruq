package com.waruq.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waruq.util.DBUtil;
import com.waruq.util.ConnectionPool;

public class TestServlet extends HttpServlet {
		
	  ConnectionPool pool;
	  Connection connection;
	  private Statement statement;
	  ResultSet resultSet;
	
	public void init() throws ServletException {
	
			pool = ConnectionPool.getInstance();
		    connection = pool.getConnection();
		
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ResultSet resultSet = null;
		try {
			// Get Connection and Statement
			statement = connection.createStatement();
			String query = "SELECT * FROM USERS";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) +"  "+ resultSet.getString(2) +"  "+ resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			DBUtil.closeResultSet(resultSet);
			DBUtil.closeStatment(statement);			
			pool.freeConnection(connection);
		}
	}
}