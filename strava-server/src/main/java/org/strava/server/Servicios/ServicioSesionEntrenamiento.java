package org.strava.server.Servicios;

import org.strava.server.Data.DAO.SesionEntrenamientoDAO;
import org.strava.server.Data.Dominio.*;

import java.util.*;

public class ServicioSesionEntrenamiento {
    SesionEntrenamientoDAO sesionEntrenamientoDAO;
    private static ServicioSesionEntrenamiento instance;

    private ServicioSesionEntrenamiento() {
        this.sesionEntrenamientoDAO = new SesionEntrenamientoDAO();
    }

    public static ServicioSesionEntrenamiento getInstance() {
        if(instance == null) {
            instance = new ServicioSesionEntrenamiento();
        }
        return instance;
    }

    public void crearSesionEntrenamiento(TokenDO tokenDo, SesionEntrenamientoNuevoDO sesionEntrenamientoNuevoDo) throws Exception {
        if(ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            UsuarioDO usuarioDo = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);
            SesionEntrenamientoDO sesionEntrenamientoDo = new SesionEntrenamientoDO(sesionEntrenamientoNuevoDo, usuarioDo);
            sesionEntrenamientoDAO.registrarSesionEntrenamiento(sesionEntrenamientoDo);
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }

    public List<SesionEntrenamientoDO> getSesionesEntrenamientos(TokenDO tokenDo) throws Exception {
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            UsuarioDO usuarioDo = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);
            return sesionEntrenamientoDAO.obtenerSesionesEntrenamiento(usuarioDo);
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
}
