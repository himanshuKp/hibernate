package com.him.student.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Student;

public class QueryStudentData {

//  O(1)
//	show list of data of students
	private static void showStudentsData(List<Student> theStudents)
	{
		if(!theStudents.isEmpty())
		{
			extracted(theStudents); 
		}else {
			System.out.println("The list is empty bro!!");
		}
	}

//	O(n)
	private static void extracted(List<Student> theStudents) {
		for (Student theStudent:theStudents)
		{
			System.out.println(theStudent);
		}
	}

	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
//		create session object
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			System.out.println("Starting");
//			begin data transaction
			session.beginTransaction();
			
//			List all the data from the database
			List<Student> theStudents = session
					.createQuery("from Student")
					.getResultList();
			
			showStudentsData(theStudents);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Ending!");
			sessionFactory.close();
		}

	}

}
