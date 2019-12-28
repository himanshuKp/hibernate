package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Course;
import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;
import com.him.student.entity.Review;
import com.him.student.entity.Student;

public class CreateCourseAndReviewDemo {
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
			
//			create a course
			Course tempCourse = new Course("Pacman - score million dollar");
			
			
//			add some reviews
			tempCourse.addReview(new Review("Good Job"));
			tempCourse.addReview(new Review("Doing great"));
			tempCourse.addReview(new Review("Doing anything"));
			tempCourse.addReview(new Review("How you doin?"));
			
//			save the course.. and leverage the cascade all
			System.out.println("Saving the course");
			session.save(tempCourse);
			
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
