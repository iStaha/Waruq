package com.waruq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.waruq.models.Product;
import com.waruq.models.User;
import com.waruq.util.ConnectionPool;
import com.waruq.util.DBUtil;

public class UserDAO {

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
			+ " (?, ?, ?);";
	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + "  (item, user_id) VALUES " + " (?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_PRODUCT_BY_USER_ID = "select id,item from products where user_id =?";
	private static final String SELECT_ALL_PRODUCTS = "select products.item, products.id from products where user_id= ?;";
	private static final String SELECT_ALL_USERS = "SELECT * FROM users, products where users.id=products.user_id  group by users.id";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update products set  user_id = ? where id = ?;";

	public void Create(User user) {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psUser = null, psProduct = null;
		ResultSet resultSet = null;

		try {
			psUser = connection.prepareStatement(INSERT_USERS_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			psProduct = connection.prepareStatement(INSERT_PRODUCTS_SQL);

			psUser.setString(1, user.getName());
			psUser.setString(2, user.getEmail());
			psUser.setString(3, user.getCountry());
			System.out.println(psUser);
			psUser.executeUpdate();

			ResultSet rs = psUser.getGeneratedKeys();
			int pp = 0;
			if (rs.next()) {
				pp = rs.getInt(1);
			}

	//		System.out.println("------INSERTED QUERY ROW ID-----" + pp);

    //		System.out.println("------INSERTED QUERY ITEM-----" + user.getList().get(0).getItem());

			psProduct.setString(1, user.getList().get(0).getItem());
			psProduct.setInt(2, pp);

			psProduct.executeUpdate();

		}

		catch (SQLException e) {
			printSQLException(e);
		}

		finally {
			DBUtil.closeResultSet(resultSet);
			DBUtil.closeStatment(psUser);
			DBUtil.closeStatment(psProduct);
			pool.freeConnection(connection);
		}

	}

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psUser = null, psProduct = null;
		ResultSet rsUser = null, rsProduct = null;

		try {
			psUser = connection.prepareStatement( SELECT_ALL_USERS);
			psProduct = connection.prepareStatement(SELECT_ALL_PRODUCTS);
			System.out.println(psUser);
			System.out.println(psProduct);
			// Step 3: Execute the query or update query
			 rsUser = psUser.executeQuery();

			System.out.println(rsUser);

			// Step 4: Process the ResultSet object.
			while (rsUser.next()) {
				List<Product> products = new ArrayList<>();
				int id = rsUser.getInt("id");
				String name = rsUser.getString("name");
				String email = rsUser.getString("email");
				String country = rsUser.getString("country");
                System.out.println("ID " + id );
				
				psProduct.setInt(1, id);
			    rsProduct = psProduct.executeQuery();

				while (rsProduct.next()) {

					String item = rsProduct.getString("item");
					  System.out.println("ItEm " + item );
					int idP = Integer.parseInt(rsProduct.getString("id"));
					products.add(new Product(idP , item));
				}

				/*
				 * String item = rs.getString("item"); String userId = rs.getString("user_id");
				 * products.add(new Product(item, userId));
				 */

				// List<Product> pro = rs.getObject();
				System.out.println(users);
				// System.out.println(country);
				// System.out.println(item);
				users.add(new User(id, name, email, country, products));

			}

		}

		catch (SQLException e) {
			printSQLException(e);
		}

		finally {
			DBUtil.closeResultSet(rsUser);
			DBUtil.closeResultSet(rsProduct);
			DBUtil.closeStatment(psUser);
			DBUtil.closeStatment(psProduct);
			pool.freeConnection(connection);
		}
		return users;
	}
	
	
    public User find(int id) {
        User user = null;
        List<Product> products = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psUser = null, psProduct = null;
		ResultSet rsUsers = null, rsProducts = null;
        try {
        	
            
            psProduct = connection.prepareStatement(SELECT_PRODUCT_BY_USER_ID);
            psProduct.setInt(1, id);
            System.out.println(psProduct);     
            rsProducts   = psProduct.executeQuery();
        
            while (rsProducts.next()) {
                int idP = Integer.parseInt(rsProducts.getString("id"));
                String item = rsProducts.getString("item");
                products.add(new Product(idP, item));
            }
            
            psUser = connection.prepareStatement(SELECT_USER_BY_ID);
        	psUser.setInt(1, id);
            System.out.println(psUser);     
            rsUsers   = psUser.executeQuery();
          
            while (rsUsers.next()) {
                String name = rsUsers.getString("name");
                String email = rsUsers.getString("email");
                String country = rsUsers.getString("country");
                user = new User(id, name, email, country, products);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        finally {
			DBUtil.closeResultSet(rsUsers);
			DBUtil.closeResultSet(rsProducts);
			DBUtil.closeStatment(psUser);
			DBUtil.closeStatment(psProduct);
			pool.freeConnection(connection);
		}
        return user;
    }
    
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdatedUser = false, rowUpdatedProduct = false;
        ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psUser = null, psProduct = null;
        try {
        	psUser = connection.prepareStatement(UPDATE_USERS_SQL);
        	psUser.setString(1, user.getName());
        	psUser.setString(2, user.getEmail());
        	psUser.setString(3, user.getCountry());
        	psUser.setInt(4, user.getId());

            rowUpdatedUser = psUser.executeUpdate() > 0;
                      
            psProduct = connection.prepareStatement(UPDATE_PRODUCTS_SQL);
            
            System.out.println("QUERY Update " +   psProduct);
            
            
            System.out.println("USER ID " +  user.getId());
           
            psProduct.setInt(1, user.getId());
            psProduct.setInt(2, user.getList().get(0).getId());
            
            rowUpdatedProduct = psProduct.executeUpdate() > 0;
            
        }
        catch (SQLException e) {
			printSQLException(e);
		}

		finally {
			DBUtil.closeStatment(psUser);
			DBUtil.closeStatment(psProduct);
			pool.freeConnection(connection);
		}
        
        return rowUpdatedUser;
    }

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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