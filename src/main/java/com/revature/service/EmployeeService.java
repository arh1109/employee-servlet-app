package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class EmployeeService {

	private EmployeeDao edao;
	
	/**
	 * Dependency Injection via Constructor Injection
	 * 
	 * Constructor Injection is a sophisticated way of ensuring
	 * that the Employee object ALWAYS has an EmployeeDao object
	 * 
	 * @param edao
	 */
	public EmployeeService(EmployeeDao edao) {
		
		this.edao = edao;
		
	}
	
	/**
	 * Our Servlet will pass the username and password to this method invocation
	 * @param username
	 * @param password
	 * @return
	 */
	public Employee confirmLogin(String username, String password) {
		
		// let's stream through all the employees that are returned
		Optional<Employee> possibleEmp = edao.findAll().stream().filter(e -> 
			e.getUsername().equals(username) && e.getPassword().equals(password)).findFirst();
		
		// If the employee is present, return it, otherwise return empty Emp object (with id of 0)

		return (possibleEmp.isPresent() ? possibleEmp.get() : new Employee());
		// ideally you should optimize this and set up a custom exception
	}
	
	public List<Employee> getAll() {
		return edao.findAll();
	}
	
}