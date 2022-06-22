package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	
    
	/**
	 * This method will be responsible for determining what resource the client is requesting
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. URI rewriting - capture the URI and rewrite it
		// http://localhost:8080/employee-servlet-app/login	-- we want to capture "/login"
		final String URI = request.getRequestURI().replace("/employee-servlet-app-original/", "");
		
		// set up a switch case statement in which we call the appropriate functionality based on the URI returned
		switch(URI) {
		case "login":
			// invoke some funciton from the RequestHelper
			RequestHelper.processLogin(request, response);
			break;
		case "employees":
			// invoke some funciton from the RequestHelper whcih will return all employees
			RequestHelper.processEmployees(request, response);
			break;
		
		case "register":
			RequestHelper.processRegistration(request, response);
			break;
		default:
			// custom error page
			break;
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
