package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Course;
import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;
import com.him.student.entity.Student;

public class EagerLazyDemo {
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
			
//			get the instructor from database
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("himanshu: Instructor details: " +tempInstructor);
			
//			get courses linked to the instructor
			System.out.println("himanshu: Courses linked to "+tempInstructor.getFirstName()+" "+tempInstructor.getLastName()+" :" +tempInstructor.getCourses()+"\n");
			
//			commit the transaction
			session.getTransaction().commit();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		finally {
//			close session
			System.out.println("himanshu: Closing session");
			session.close();
			
//			close session factory 
			System.out.println("himanshu: Done!!!");
			sessionFactory.close();
		}
		
	}
}
