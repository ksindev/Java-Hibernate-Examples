package com.relevancelab.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.relevancelab.hibernate.demo.entity.Course;
import com.relevancelab.hibernate.demo.entity.Instructor;
import com.relevancelab.hibernate.demo.entity.InstructorDetail;
import com.relevancelab.hibernate.demo.entity.Student;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			System.out.println("Fetch the instructor object...");
			
			session.beginTransaction();
			
			int theId = 1;
			Instructor theInstructor = session.get(Instructor.class, theId); 
			
			System.out.println("The instructor is "+theInstructor);
			
			System.out.println("Create new courses...");
			
			Course course1 = new Course("Introduction to Java");
			Course course2 = new Course("J2EE");
			
			theInstructor.addCourse(course1);
			theInstructor.addCourse(course2);
			
			System.out.println("Saving the courses...");
			
			session.save(course1);
			session.save(course2);
			
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
