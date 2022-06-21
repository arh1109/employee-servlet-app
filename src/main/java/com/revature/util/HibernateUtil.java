package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * This is the Hibernate Helper class
 * which is to handle startup and access Hibernate's 
 * Session Factory to obtain a Session Object (connection to DB)
 * @author andrewhughes
 *
 */
public class HibernateUtil {

	private static Session ses; //kind of like the Connection Inteface in JDBC
	private static SessionFactory sf = new Configuration()
			.configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() { // similar to getConnection()
		
		// call on our SessionFactory to open a connection if there isn't one
		if(ses == null) {
			ses = sf.openSession();
		}
		return ses;  // if the session is not null, just return it.
	}
	
	public static void closeSes() {
		// ideally when we close a session it frees up that connection to the
		// DB and returns it to the connectin pool so that it can be used
		// by another thread or operation.
		ses.close();
	}
	
	
}

