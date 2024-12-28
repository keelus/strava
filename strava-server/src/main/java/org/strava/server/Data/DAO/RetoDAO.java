package org.strava.server.Data.DAO;

import org.strava.server.Data.Dominio.RetoDO;
import org.strava.server.Data.Dominio.UsuarioDO;
import org.strava.server.StravaEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

public class RetoDAO {
    private EntityManager entityManager;
    public RetoDAO() {
        this.entityManager = StravaEntityManager.getEntityManager();
    }

    public RetoDO obtenerReto(Long id) {
        return entityManager.find(RetoDO.class, id);
    }

    public List<RetoDO> obtenerRetosActivos(Date fechaLimite) {
        String queryStr = "SELECT r FROM RetoDO r WHERE r.fechaFin > :fechaLimite";
        TypedQuery<RetoDO> query = entityManager.createQuery(queryStr, RetoDO.class);
        query.setParameter("fechaLimite", fechaLimite);

        return query.getResultList();
    }

    public void registrarReto(RetoDO retoDo) {
        entityManager.getTransaction().begin();
        entityManager.persist(retoDo);
        entityManager.getTransaction().commit();
    }

    public void aceptarReto(UsuarioDO usuario, RetoDO reto) {
        entityManager.getTransaction().begin();

        usuario.getRetosAceptados().add(reto);

        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
    }
}
