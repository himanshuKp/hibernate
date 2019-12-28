package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Course;
import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;
import com.him.student.entity.Student;

public class CreateCourseDemo {
	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
//		start session 
		Session session = sessionFactory.getCurrentSession();
		
		try {
		
//			start transaction
			session.beginTransaction();
			
			
//			get instructor from db
			int theId = 1;
			Instructor instructor = session.get(Instructor.class,
					theId);
			
//			create some courses
			Course tempCourse1 = new Course("Introduction to DB");
			Course tempCourse2 = new Course("Application development using spring");
			
//			add courses to instructor
			instructor.add(tempCourse1);
			instructor.add(tempCourse2);
			
//			save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
//			commit the transaction
			session.getTransaction().commit();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		finally {
//			close session
			System.out.println("Closing session");
			session.close();
			
//			close session factory 
			System.out.println("Done!!!");
			sessionFactory.close();
		}
		
	}
}
