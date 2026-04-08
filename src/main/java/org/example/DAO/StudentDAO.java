package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.connection.Connection;
import org.example.model.Student;

import java.util.Collections;
import java.util.List;

public class StudentDAO {

    public void create(Student student) {
        EntityManager entityManager = Connection.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Student> read() {
        EntityManager entityManager = Connection.getEntityManager();

        List<Student> students = entityManager.createQuery("FROM Student", Student.class).getResultList();

        entityManager.close();
        return students;
    }

    public List<Student> searchToEmail(String email) {
        EntityManager entityManager = Connection.getEntityManager();

        TypedQuery<Student> query = entityManager.createQuery("FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);

        List<Student> students = Collections.singletonList(query.getResultStream().findFirst().orElse(null));
        entityManager.close();

        return students;
    }
}
