package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Course;
import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;
import com.him.student.entity.Review;
import com.him.student.entity.Student;

public class AddCoursesForRaviDemo {
	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
//		start session 
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
//			start transaction
			session.beginTransaction();
			
//			get ravi from the database
			int theId = 2;
			Student theStudent = session.get(Student.class, theId);
			
//			courses assigned to the student
			System.out.println("Courses assigned to student: " +theStudent.getCourses());
			
//			create more courses
			Course course1 = new Course("Science 101");
			Course course2 = new Course("History 101");
			Course course3 = new Course("Drawing 101");
			
//			add student to course
			course1.addStudent(theStudent);
			course2.addStudent(theStudent);
			course3.addStudent(theStudent);
			
//			save the course
			System.out.println("Saving the courses and student with it");
			session.save(course1);
			session.save(course2);
			session.save(course3);
			
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
