package org.strava.server.Data.DAO;

import org.strava.server.Data.Dominio.SesionEntrenamientoDO;
import org.strava.server.Data.Dominio.UsuarioDO;
import org.strava.server.StravaEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.strava.server.StravaEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class SesionEntrenamientoDAO {
    private EntityManager entityManager;
    public SesionEntrenamientoDAO() {
        this.entityManager = StravaEntityManager.getEntityManager();
    }

    public SesionEntrenamientoDO obtenerSesionEntrenamiento(Long id) {
        return entityManager.find(SesionEntrenamientoDO.class, id);
    }

    public List<SesionEntrenamientoDO> obtenerSesionesEntrenamiento(UsuarioDO autor) {
        String queryStr = "SELECT s FROM SesionEntrenamientoDO s WHERE s.autor = :autor";
        TypedQuery<SesionEntrenamientoDO> query = entityManager.createQuery(queryStr, SesionEntrenamientoDO.class);
        query.setParameter("autor", autor);

        return query.getResultList();
    }

    public void registrarSesionEntrenamiento(SesionEntrenamientoDO sesionEntrenamientoDo) {
        entityManager.getTransaction().begin();
        entityManager.persist(sesionEntrenamientoDo);
        entityManager.getTransaction().commit();
    }
}
