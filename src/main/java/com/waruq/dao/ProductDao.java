package com.waruq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.waruq.models.Product;
import com.waruq.util.ConnectionPool;
import com.waruq.util.DBUtil;



public class ProductDao {
	


	    private static final String INSERT_USERS_SQL = "INSERT INTO products" + "  (item, user_id) VALUES " +
	        " (?, ?);";
	    
	    private static final String SELECT_ALL_PRODUCTS = "SELECT * from products";
	    
	    private static final String SELECT_PRODUCT_BY_ID = "select id,  item, user_id from products where id =?";
	    
	    
	    
	    public ProductDao() {}

	    
	    public void insertProduct(Product product) throws SQLException {
	        System.out.println(INSERT_USERS_SQL);
	        
	        ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement  psProduct = null;
	        // try-with-resource statement will auto close the connection.
	        try  {
	        	psProduct = connection.prepareStatement(INSERT_USERS_SQL);
	        	psProduct.setString(1, product.getItem());
	        	psProduct.setString(2, product.getUser_id());
	            System.out.println(psProduct);
	            psProduct.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        finally {
				DBUtil.closeStatment(psProduct);
				pool.freeConnection(connection);
			}
	    }
	    
	    
	    public Product selectProduct(int id) {
	        Product product = null;
	        
	        ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement  psProduct = null;
			ResultSet rsProduct = null;
	        
	        
	        try {
	        	psProduct   = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
	        	psProduct .setInt(1, id);
	            System.out.println(psProduct );
	            // Step 3: Execute the query or update query
	            rsProduct = psProduct.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rsProduct.next()) {
	            	int i = rsProduct.getInt("id");
	                String item = rsProduct.getString("item");
	                product = new Product(i, item);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return product;
	    }

	    public List <Product> selectAllProducts() {
	        System.out.println("SELECT_ALL_PRODUCTS");

			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement  psProduct = null;
			ResultSet rsProduct = null;
			
	        List <Product> products = new ArrayList <> ();
	        // try-with-resource statement will auto close the connection.
	        try  {
	        	  psProduct = connection.prepareStatement(SELECT_ALL_PRODUCTS);
	        	  System.out.println(psProduct);
	              // Step 3: Execute the query or update query
	              rsProduct = psProduct.executeQuery();
	              // Step 4: Process the ResultSet object.
	              while (rsProduct.next()) {
	            	  int id = rsProduct.getInt("id");
	                  String item = rsProduct.getString("item");
	                  String userId = rsProduct.getString("user_id");
	                
	                  products.add(new Product(id, item, userId));
	                        	               
	              }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        
	        finally {
				DBUtil.closeStatment(psProduct);
				DBUtil.closeResultSet(rsProduct);
				pool.freeConnection(connection);
			}
	        System.out.println("Products in List "  + products);
	        
	        return products;
	    }
	    
	    
	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }


}
