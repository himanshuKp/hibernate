package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Instructor.class)
				.buildSessionFactory();
		
//		start session
		Session session = sessionFactory.getCurrentSession();

		try {
			
//			begin transaction
			session.beginTransaction();			
			
//			get id of item to be deleted
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
//			delete the entry from the database
			if(tempInstructor!=null) {
				
				System.out.println("Found: " +tempInstructor);
				
				session.delete(tempInstructor);
				
			}else {
				System.out.println("Deletion of item is not done!!");
			}
					
//			commit the transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Deletion done!!");
			sessionFactory.close();
		}
		
	}

}
