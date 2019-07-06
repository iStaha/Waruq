package com.waruq.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waruq.dao.ProductDao;
import com.waruq.dao.UserDAO;
import com.waruq.models.Product;
import com.waruq.models.User;






@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private ProductDao productDao;

    public void init() {
        userDAO = new UserDAO();
        productDao = new ProductDao();

    }

   

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                	System.out.println("NEW FORM");
                    showNewForm(request, response);
                  
                    break;
                case "/insert":
                	 System.out.println("IN INSERT");
                    insertUser(request, response);
                   
                    break;
                case "/delete":
                    deleteUser(request, response);
                    System.out.println("IN DELETE");
                    break;
                case "/edit":
                    showEditForm(request, response);
                    System.out.println("IN EDIT");
                    break;
                case "/update":
                	System.out.println("IN Update");
                    updateUser(request, response);
                    break;
                case "/list":
                	System.out.println("IN LISTT");
                    listUser(request, response);
                    break;
                case "/add":
                	System.out.println("IN ADD");
                    insertProduct(request, response);
                  
                    break;
			
			 // default: listUser(request, response); break;
			 
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List <User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);        
        System.out.println("LIST   "+ listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-user.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
    	request.setAttribute("products", productDao.selectAllProducts()); 
    	System.out.println("Products " + productDao.selectAllProducts());
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        request.setAttribute("products", productDao.selectAllProducts()); 
       System.out.println("LIST    " + existingUser.getList().get(0));
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        List<Product> products = new ArrayList <> ();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String item = request.getParameter("item");
        
     //   Product pro=  productDao.selectProduct(Integer.parseInt(itemId));
        
       
       
        products.add(new Product(item));
        User newUser = new User(name, email, country, products);
        userDAO.Create(newUser);
        response.sendRedirect("user-form.jsp");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	 List<Product> products = new ArrayList <> ();
        Product product;
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String item = request.getParameter("item");
     
    //    System.out.println("ITEM "  + item);
        
        product = productDao.selectProduct(Integer.parseInt(item));
        
        System.out.println("Product to be Updated " +  product);
        
        product.setUser_id(request.getParameter("id"));
        
        
        products.add(product);
        
        User book = new User(id, name, email, country, products);
       
        userDAO.updateUser(book);
        
        response.sendRedirect("list");
 
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
  

    }


    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {

    
    }

}
