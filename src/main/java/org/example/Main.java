package org.example;

import org.example.entities.Certificate;
import org.example.entities.Student;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Student student = new Student();
        student.setName("Tanish Padwal");
        student.setCollege("SIES");
        student.setFatherName("Sanjay Padwal");
        student.setPhone("1234567890");
        student.setActive(true);
        student.setAbout("Artist");

        Certificate certificate = new Certificate();
        certificate.setTitle("Java Certification");
        certificate.setAbout("This is a java certificate");
        certificate.setLink("link");
        certificate.setStudent(student);

        Certificate certificate1 = new Certificate();
        certificate1.setTitle("Python Certification");
        certificate1.setAbout("This is a python certificate");
        certificate1.setLink("link");
        certificate1.setStudent(student);

        student.getCertificates().add(certificate);
        student.getCertificates().add(certificate1);

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