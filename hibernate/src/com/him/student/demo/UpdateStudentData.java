package com.him.student.demo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Student;

public class UpdateStudentData {

	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
//		create session object
		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();
			
			/*
			 * 
			 * session.createQuery("update Student set email 
			 * = "foog@gmail.com" where email like '%gmail.com'").
			 * executeQuery();
			 * 		 * 
			 * */
			
			int studentId = 5;
				
			Student myStudent = session.get(Student.class,studentId);
			
//			still in memory
			myStudent.setEmail("deepu@gmail.com");
			
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Done!");
			sessionFactory.close();
		}
		
	}

}
