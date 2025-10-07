package org.example;

import org.example.entities.Student;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Student student = new Student();
        student.setName("Bhawarth Padwal");
        student.setCollege("SIMS");
        student.setFatherName("Uday Padwal");
        student.setPhone("1234567890");
        student.setActive(true);
        student.setAbout("Software Developer");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        // System.out.println(sessionFactory);

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            System.out.println("Student saved successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}