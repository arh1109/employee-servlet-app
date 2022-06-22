package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

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
		tx.commit();
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
	
	// Tested and works
	public Employee findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		Employee emp = (Employee)ses.createQuery("from Employee where id=:id").setParameter("id", id).getSingleResult();

		return emp;
	}
	
	// Delete, tested and works
	public boolean delete(int id) {
		Session ses = HibernateUtil.getSession();
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		Query query = ses.createQuery("delete from Employee where id=:id").setParameter("id", id);
		int result = query.executeUpdate();
		
		tx.commit();
		return result==1;
	}
	
	// Update, tested and works
	public boolean update(Employee e) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(e);
		
		tx.commit();
		return true;
	}
	
}
