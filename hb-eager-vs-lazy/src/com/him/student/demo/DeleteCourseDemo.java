package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Course;
import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
//		start session
		Session session = sessionFactory.getCurrentSession();

		try {
			
//			begin transaction
			session.beginTransaction();			
			
//			get id of course to be deleted
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
//			delete the entry from the database
			if(tempCourse!=null) {
				
				System.out.println("Found: " +tempCourse);
				
				session.delete(tempCourse);
				
				System.out.println("Course is deleted.");
				
			}else {
				System.out.println("Deletion of course is not done, invalid course!!");
			}
					
//			commit the transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			clear up the session
			session.close();			
			
			System.out.println("Deletion done!!");
			sessionFactory.close();
		}
		
	}

}
