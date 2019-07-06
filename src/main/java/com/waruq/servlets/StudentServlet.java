package com.waruq.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/student.jsp";
	private static String LIST_USER = "/listUser.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	//	  User user = new User();
	//        user.setFirstName(request.getParameter("firstName"));
	//        user.setLastName(request.getParameter("lastName"));
	//        try {
	//            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
	//            user.setDob(dob);
	//        } catch (ParseException e) {
	//            e.printStackTrace();
	//        }
	 //       user.setEmail(request.getParameter("email"));
	 //       String userid = request.getParameter("userid");
	 //       if(userid == null || userid.isEmpty())
	 //       {
	  //          dao.addUser(user);
	   //     }
	  //      else
	   //     {
	  //          user.setUserid(Integer.parseInt(userid));
	  //          dao.updateUser(user);
	   //     }
	        RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
	   //     request.setAttribute("users", dao.getAllUsers());
	        
	        System.out.println("POST");
	        view.forward(request, response);
	  
			
	}
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String forward="";
     //   String action = request.getParameter("action");
        String action = request.getServletPath();
        System.out.println(action);
        
        if(action==null) {
        	action= "";
        }

        if (action.equalsIgnoreCase("delete")){
     //       int userId = Integer.parseInt(request.getParameter("userId"));
        //    dao.deleteUser(userId);
            forward = LIST_USER;
            System.out.println("delete");
      //      request.setAttribute("users", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("edits")){
            forward = INSERT_OR_EDIT;
            System.out.println("edit");
            int userId = Integer.parseInt(request.getParameter("userId"));
         //   User user = dao.getUserById(userId);
     //       request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
        	 System.out.println("LISTUSER");
            forward = LIST_USER;
           // request.setAttribute("users", dao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
 
	}



}
