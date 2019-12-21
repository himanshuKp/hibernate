package com.him.student.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.him.student.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
//		create sessiono factory object
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
//		start session 
		Session session = sessionFactory.getCurrentSession();

		try {
			
//			create 3 student objects
			Student tempStudent1 = new Student("Ravi","Kandpal","raj@gmail.com");
			Student tempStudent2 = new Student("Nandi","Kandpal","nandi@gmail.com");
			Student tempStudent3 = new Student("Shekhar","Kandpal","sk@gmail.com");
			
//			begin transaction
			session.beginTransaction();
			
//			save the student object
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
//			commit the transaction
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("There is an issue while saving data");
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
		
	}

}
