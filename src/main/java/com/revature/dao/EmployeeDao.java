package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {

	// CRUD methods
	
	// Create (think that in the service layer we'll have a REGISTER)
	public int insert(Employee e) {
		
		// grab the session object
		Session ses = HibernateUtil.getSession();
		
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		// capture the pk returned when the sesion method save() is called
		int pk = (int)ses.save(e);
		
		// return the pk
		return pk;
	}
	
	// Read
	public List<Employee> findAll() {
		// grab the session object
		Session ses = HibernateUtil.getSession();
		
		// Put class name in HQL not table name!
		List<Employee> employees = ses.createQuery("from Employee").list();
		return employees;
	}
	
	// Delete
	public boolean delete(int id) {
		return false;
	}
	
	// Update
	public boolean update(int it) {
		return false;
	}
	
}
