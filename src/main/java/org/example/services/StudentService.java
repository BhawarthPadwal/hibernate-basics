package org.example.services;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Student;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.util.List;

public class StudentService {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void saveStudent(Student student) {
        try (Session session = sessionFactory.openSession()){
            Transaction beginTransaction = session.beginTransaction();
            session.persist(student);
            beginTransaction.commit();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public Student getById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Student student = session.get(Student.class, id);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student updateStudent(long id, Student student) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student oldStudent = session.get(Student.class, id);
            if (oldStudent != null) {
                oldStudent.setName(student.getName());
                oldStudent.setPhone(student.getPhone());
                oldStudent = session.merge(oldStudent);
            }
            transaction.commit();
            return oldStudent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteStudent(long studentId) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.remove(student);
            }
            transaction.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // HQL [JPA]

    public List<Student> getAllStudentsHQL() {

        try(Session session = sessionFactory.openSession()) {
            String getHQL = "FROM Student";
            Query query = session.createQuery(getHQL, Student.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student getStudentByNameHQL(String name) {
        try(Session session = sessionFactory.openSession()) {
            String getByNameHQL = "FROM Student WHERE name = :studentName";
            Query query = session.createQuery(getByNameHQL, Student.class);
            query.setParameter("studentName", name);
            return (Student) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // CRITERIA API
    public List<Student> getStudentsByCollegeCriteria(String college) {
        try(Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);
            query.select(root).where(criteriaBuilder.equal(root.get("college"), college));
            Query query1 = session.createQuery(query);
            return query1.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> getStudentWithPagination(int pageNo, int pageSize) {
        try(Session session = sessionFactory.openSession()) {

            String pageQuery = "FROM Student";
            Query query = session.createQuery(pageQuery, Student.class);
            query.setFirstResult((pageNo - 1)*pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
