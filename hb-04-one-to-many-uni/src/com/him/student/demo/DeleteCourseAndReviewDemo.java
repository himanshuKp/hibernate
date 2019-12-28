package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Course;
import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;
import com.him.student.entity.Review;

public class DeleteCourseAndReviewDemo {
	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
//		start session 
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
//			start transaction
			session.beginTransaction();
			
//			get the course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
//			print the course
			System.out.println(tempCourse.getCourseTitle());
			
//			print the course reviews
			System.out.println(tempCourse.getReview());
			
//			delete the course and review also (@OneToMany between course and review)
			session.delete(tempCourse);
			
//			commit the transaction
			System.out.println("Doin commit!");
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
