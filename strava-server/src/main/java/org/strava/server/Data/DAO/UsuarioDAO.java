package org.strava.server.Data.DAO;

import org.strava.server.Data.Dominio.UsuarioDO;
import org.strava.server.StravaEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UsuarioDAO {
    private EntityManager entityManager;
    public UsuarioDAO() {
        this.entityManager = StravaEntityManager.getEntityManager();
    }

    public UsuarioDO obtenerUsuarioPorEmail(String email) {
        String queryStr = "SELECT u FROM UsuarioDO u WHERE u.email = :email";
        TypedQuery<UsuarioDO> query =entityManager.createQuery(queryStr, UsuarioDO.class);
        query.setParameter("email", email);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    public UsuarioDO obtenerUsuario(Long id) {
        return entityManager.find(UsuarioDO.class, id);
    }

    public void registrarUsuario(UsuarioDO usuario) {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
    }
}
