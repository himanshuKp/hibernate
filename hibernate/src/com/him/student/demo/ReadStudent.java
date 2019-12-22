package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Student;

public class ReadStudent {

	public static void main(String[] args) {
		
//		create session factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();

		try {
		
//			create student object from student class
			Student myStudent = new Student("Deepti","Joshi","dj@gmail.com");
			System.out.println("Saving the new student");			
//			start transaction
			session.beginTransaction();
			
//			save the student
			session.save(myStudent);
			
			System.out.println("Generated user id is: " +myStudent.getId());

//			commit the transaction
			session.getTransaction().commit();
			
//			reading data from the database
			
//			now get a new session
			session = sessionFactory.getCurrentSession();
			
//			begin transaction
			session.beginTransaction();
			
			System.out.println("Received student id is: " +myStudent.getId());
			
//			read student data from primary key = 'id'
			Student readStudent = session.get(Student.class, myStudent.getId());
						
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sessionFactory.close();
		}
		
	}

}
