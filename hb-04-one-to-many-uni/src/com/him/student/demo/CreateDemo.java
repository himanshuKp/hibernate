package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Instructor;
import com.him.student.entity.InstructorDetail;
import com.him.student.entity.Student;

public class CreateDemo {
	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
//		start session 
		Session session = sessionFactory.getCurrentSession();
		
		try {
			/*
//			create the objects
			Instructor tempInstructor = new Instructor("Himanshu","kandpal",
					"darby@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://youtube.com/hk",
							"football");
			*/
			
//			create the objects
			Instructor tempInstructor = new Instructor("Ravi","kandpal",
					"raj@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://youtube.com/rk",
							"singing");
			
//			assosicate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
//			start transaction
			session.beginTransaction();
			
//			save the constructor
//			this will also save the details object
//			because of CascadeType.ALL
			System.out.println("Saving instructor: " +tempInstructor);
			session.save(tempInstructor);
			
//			commit the transaction
			session.getTransaction().commit();
			
			
			
		} finally {
//			close session
			System.out.println("Done!!!");
			sessionFactory.close();
		}
		
	}
}
