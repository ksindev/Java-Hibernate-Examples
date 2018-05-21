package com.relevancelab.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.relevancelab.hibernate.demo.entity.Instructor;
import com.relevancelab.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorExample {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			System.out.println("Creating new instructor object...");
			
			Instructor newInstructor = new Instructor("Anjali", "Pal", "APal@relevancelab.com");
			
			InstructorDetail newInstructorDetail = 
					new InstructorDetail(
							"https://www.youtube.com/channel/AP",
							"PHP");
			
			newInstructor.setInstructorDetail(newInstructorDetail);
			
			session.beginTransaction();
			
			System.out.println("Saving the instructor...");
			session.save(newInstructor);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
							
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			//handle connection leak
			session.close();
			
			factory.close();
		}

	}

}
