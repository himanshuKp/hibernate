package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Student;

public class CreateStudent {
	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
//		start session 
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
//			create student class object
			Student tempStudent = new Student("Himanshu","Kandpal",
					"himanshukp324@gmail.com");
			
//			start transaction
			session.beginTransaction();
			
//			save student object
			session.save(tempStudent);
			
//			commit the transaction
			session.getTransaction().commit();
			
			
			
		} finally {
//			close session
			sessionFactory.close();
		}
		
	}
}
