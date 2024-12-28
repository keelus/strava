package org.strava.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StravaEntityManager {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("StravaPersistencia");

    private static EntityManager entityManager = null;

    public static EntityManager getEntityManager() {
        if(entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void close() {
        entityManagerFactory.close();
    }
}
