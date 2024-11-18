package org.strava.server.Servicios;

import org.strava.server.Data.Dominio.SesionEntrenamientoDO;

import org.strava.server.Data.Dominio.SesionEntrenamientoNuevoDO;
import org.strava.server.Data.Dominio.TokenDO;

import java.util.*;

public class ServicioSesionEntrenamiento {
    private static ServicioSesionEntrenamiento instance;
    private final Map<Long, SesionEntrenamientoDO> sesionesEntrenamiento = new HashMap<>();

    private ServicioSesionEntrenamiento() {
    }

    public static ServicioSesionEntrenamiento getInstance() {
        if(instance == null) {
            instance = new ServicioSesionEntrenamiento();
        }
        return instance;
    }

    public void crearSesionEntrenamiento(TokenDO tokenDo, SesionEntrenamientoNuevoDO sesionEntrenamientoNuevoDo) throws Exception {
        if(ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            SesionEntrenamientoDO sesionEntrenamientoDo = new SesionEntrenamientoDO(sesionEntrenamientoNuevoDo, Long.valueOf(sesionesEntrenamiento.size()));
            sesionesEntrenamiento.put(sesionEntrenamientoDo.getId(), sesionEntrenamientoDo);
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
}
