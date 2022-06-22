package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {

	private EmployeeService eserv;
	private EmployeeDao mockdao;
	
	@Before
	public void setup() {
		
		mockdao = mock(EmployeeDao.class);
		eserv = new EmployeeService(mockdao);
		
	}
	
	@After
	public void teardown() {
		
		mockdao = null;
		eserv = null;
		
	}
	
	@Test
	public void testConfirmLogin_Success() {
		
		// 1. Create a fake list of emps
		// this is the dummyh data we feed to MOckito
		Employee e1 = new Employee(20, "Bruce", "Banner", "thehulk", "green");
		Employee e2 = new Employee(21, "Clint", "Barton", "hawkeye", "arrows");
		
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);
		
		// 2. Seup up the mock dao's behavior
		// findAll() method's behavior to provide fake data
		when(mockdao.findAll()).thenReturn(emps);
		
		// 3. capture the actual output of the method
		Employee actual = eserv.confirmLogin(e1.getUsername(), e1.getPassword());
		// capture the expected output of the  methods.
		Employee expected = e1;
		// assert that they're equal
		assertEquals(actual, expected);
	}
	
	@Test
	public void testConfirmLogin_Fail() {
		// 1. Create a fake list of emps
				// this is the dummyh data we feed to MOckito
				Employee e1 = new Employee(20, "Bruce", "Banner", "thehulk", "green");
				Employee e2 = new Employee(21, "Clint", "Barton", "hawkeye", "arrows");
				
				List<Employee> emps = new ArrayList<Employee>();
				emps.add(e1);
				emps.add(e2);
				
				// 2. Seup up the mock dao's behavior
				// findAll() method's behavior to provide fake data
				when(mockdao.findAll()).thenReturn(emps);
				
				// 3. capture the actual output of the method
				Employee actual = eserv.confirmLogin(e1.getUsername(), e1.getPassword());
				// capture the expected output of the  methods.
				Employee expected = new Employee();
				// assert that they're equal
				assertNotEquals(actual, expected);
				
	}
	
}
