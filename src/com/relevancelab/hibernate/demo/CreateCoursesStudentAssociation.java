package com.relevancelab.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.relevancelab.hibernate.demo.entity.Course;
import com.relevancelab.hibernate.demo.entity.Instructor;
import com.relevancelab.hibernate.demo.entity.InstructorDetail;
import com.relevancelab.hibernate.demo.entity.Student;

public class CreateCoursesStudentAssociation {

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
			
			System.out.println("\nApp:Fetch the course object...");
			
			session.beginTransaction();
			
			int theId = 10;
			Course course1 = session.get(Course.class, theId); 
			
			System.out.println("\nApp: The course is "+course1);
			
			System.out.println("\nApp: Creating new students...");
			
			Student student1 = new Student("Harish", "Gopal", "hgopal@gmail.com");
			Student student2 = new Student("George", "Martin", "georgem@gmail.com");
			Student student3 = new Student("Nancy", "Friday", "nfday@gmail.com");
			
			course1.addStudent(student1);
			course1.addStudent(student2);
			course1.addStudent(student3);
	
			System.out.println("\nApp: Saving the students...");
			
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			System.out.println("\nApp:Fetch the course object...");
			
			theId = 11;
			Course course2 = session.get(Course.class, theId);
			
			System.out.println("\nApp: The course2 is "+course2);
			
			System.out.println("\nApp: The student3 is "+student3);
			
			System.out.println("\nApp: Adding the student3 to course2...");
			
			student3.addCourse(course2);
			
			System.out.println("\nApp: Saving the course2...");
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
