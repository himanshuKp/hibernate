package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;

public class BiDemo {

	public static void main(String[] args) {

//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
//		create session object
		Session session = sessionFactory.getCurrentSession();
	
		try {	
//			start transaction
			session.beginTransaction();
			
//			create the objects
			int theId = 1;
			InstructorDetail instructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			System.out.println("Instructor detail class "+instructorDetail);
			
			System.out.println("The associated object: " +instructorDetail.getInstructor());
			
			session.getTransaction().commit();
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// TODO: handle finally clause
//			handle session connection 
			session.close();			
			
//			handle factory session
			System.out.println("Done!");
			sessionFactory.close();
		}
	}

}
