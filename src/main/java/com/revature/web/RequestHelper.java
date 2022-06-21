package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

/**
 * aka a Dispatcher in the FrontController pattern. Not a Servlet
 * @author andrewhughes
 *
 */
public class RequestHelper {

	// employeeservice
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	
	// ObjectMapper (for frontend)
	private static ObjectMapper om = new ObjectMapper();
	
	/**
	 * Extracts the parameters from a request (username and password) from the UI.
	 * It will call the confirmLogin() method from the EmployeeService and see
	 * if a user with that username + password exist
	 * 
	 * Who will provide the method with the HttpRequest? The UI
	 * We need to build an html doc with a form that will send these parameters to the method.
	 */
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		
		// 1. Extract the parameters from the request (username + password)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 2. call the confirm login(0 method from the employeeService and see what it returns)
		Employee e = eserv.confirmLogin(username, password);
		
		
		// 3. I fthe usre exists, let's print their info to the screen . Or redirect the request
		if(e.getId() > 0) {
			// grab the session
			HttpSession sess = request.getSession();
			
			// add the user to the session
			sess.setAttribute("the-user", e);
			
			// alternatively you can redirect to another resourse
			
			// print out the user's data with the print writer
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Welcome " + e.getFirstName() + "!</h1>");
			out.println("<h3>You have successfully logged in!</h3>");
			
			// you COULD print the object out a s a JSON string
			String jsonString = om.writeValueAsString(e);
			out.println(jsonString);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No User found sorry");
//			response.setStatus(204);  // 204 means successful connection to the server, but no content found
		}
	
	}
}
