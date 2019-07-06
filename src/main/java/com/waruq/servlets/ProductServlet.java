package com.waruq.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
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
                case "/add":
                	System.out.println("IN ADD");
                    insertProduct(request, response);
                  
                    break;
                case "/product":
                	System.out.println("IN PRODUCT");
                	  RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
                      dispatcher.forward(request, response);
                    break;
			
			 default: insertProduct(request, response); break;
			
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }



    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
        dispatcher.forward(request, response);
    }

 
}
