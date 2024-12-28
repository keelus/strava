package org.strava.server.Servicios;

import org.strava.server.Data.DAO.RetoDAO;
import org.strava.server.Data.Dominio.RetoDO;
import org.strava.server.Data.Dominio.RetoNuevoDO;
import org.strava.server.Data.Dominio.TokenDO;
import org.strava.server.Data.Dominio.UsuarioDO;

import java.util.*;

public class ServicioReto {
    RetoDAO retoDAO;
    private static ServicioReto instance;

    private ServicioReto() {
        this.retoDAO = new RetoDAO();
    }

    public static ServicioReto getInstance() {
        if(instance == null) {
            instance = new ServicioReto();
        }
        return instance;
    }

    public void crearReto(TokenDO tokenDo, RetoNuevoDO retoNuevoDo) throws Exception {
        // Comprobar si el token es valido
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            UsuarioDO usuarioDo = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);

            RetoDO retoDo = new RetoDO(retoNuevoDo);
            retoDo.setAutor(usuarioDo);
            retoDAO.registrarReto(retoDo);
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
    public void aceptarReto(TokenDO tokenDo, Long retoId) throws Exception {
        // Comprobar si el token es valido
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            RetoDO retoObtenido = retoDAO.obtenerReto(retoId);

            if(retoObtenido != null) {
                UsuarioDO usuario = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);
                retoDAO.aceptarReto(usuario, retoObtenido);
            } else {
                throw new Exception("El reto no existe.");
            }

        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }

    public List<RetoDO> getRetosActivos(TokenDO tokenDo, Date fechaLimite) throws Exception {
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            return retoDAO.obtenerRetosActivos(fechaLimite);
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }

    public List<RetoDO> getRetosAceptados(TokenDO tokenDo) throws Exception {
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            UsuarioDO usuario = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);
            return usuario.getRetosAceptados();
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }

    public boolean isRetoAceptadoPorUsuario(TokenDO tokenDo, Long retoId) throws Exception {
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            RetoDO retoObtenido = retoDAO.obtenerReto(retoId);

            if(retoObtenido != null) {
                UsuarioDO usuarioDo = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);
                return usuarioDo.getRetosAceptados().contains(retoObtenido);
            } else {
                return false;
            }
        } else {
            throw new Exception("El reto no existe.");
        }
    }
}
