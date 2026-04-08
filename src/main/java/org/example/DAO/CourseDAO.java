package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.connection.Connection;
import org.example.model.Course;

import java.util.Collections;
import java.util.List;

public class CourseDAO {

    public void create(Course course) {
        EntityManager entityManager = Connection.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Course> read() {
        EntityManager entityManager = Connection.getEntityManager();

        List<Course> courses = entityManager.createQuery("FROM Course ", Course.class).getResultList();

        entityManager.close();
        return courses;
    }

    public List<Course> searchToName(String name) {
        EntityManager entityManager = Connection.getEntityManager();

        TypedQuery<Course> query = entityManager.createQuery("FROM Course c WHERE LOWER(c.name) LIKE LOWER(:name)", Course.class);
        query.setParameter("name", "%" + name + "%");

        List<Course> courses = Collections.singletonList(query.getResultStream().findFirst().orElse(null));
        entityManager.close();

        return courses;
    }

}
