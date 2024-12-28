package org.strava.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StravaEntityManager {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("StravaPersistencia");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }
}
