package org.example.DAO;

import jakarta.persistence.EntityManager;
import org.example.connection.Connection;
import org.example.model.Registration;

import java.util.List;

public class RegistrationDAO {

    public void create(Registration registration) {
        EntityManager entityManager = Connection.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(registration);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Registration> read() {
        EntityManager entityManager = Connection.getEntityManager();

        return entityManager.createQuery("FROM Registration ", Registration.class).getResultList();
    }
}
