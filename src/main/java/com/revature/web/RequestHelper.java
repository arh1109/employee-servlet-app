package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
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
	
	/*
	 * Call the EmployeeService's findAll method()
	 * Use an object mapper to transform that list to a JSON string
	 * Then use the print writer to print out that JSON string to the screen
	 */
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// http://localhost8880/employee-servlet-app/employees
		// will return an entire list of all employees in JSON
		
		// 1. set the contetn type to be application/json
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.setContentType("text/");
		
		// 2. call the findAll() method form the employee service
		List<Employee> emps = eserv.getAll();
		
		// 3. transform the list to a string
		String jsonString = om.writeValueAsString(emps);
		
		// write it out
		PrintWriter out = response.getWriter();
		
		out.write(jsonString);		// out.write sends it as response body
		
	}
	
	public static void processRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. extract all values from the parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		// 2. construct a new employee object
		Employee e = new Employee(firstname, lastname, username, password);
		
		// 3. call the register() method from the service layer
		int pk = eserv.register(e);
		
		// 4. check it's ID...if it's > 0 it's successful
			
		if(pk > 0) {
			e.setId(pk);
			// add the user to the session
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			// using the request dispatches, forward the request and response to a new resource...
			// send the user to a new page --welcome.html
			request.getRequestDispatcher("welcome.html").forward(request, response);
		} else {
			// provide better logic in the Service layer to check for PSQL exceptions
					// if it's -1, that means the register method failed (and there's probably a duplicate user)
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Registration failed. User already exists!</h1>");
			out.println("<a href=\"index.html\">Back</a>");
		}
	}
	
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
