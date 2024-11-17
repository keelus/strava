package org.strava.server.Servicios;

import org.strava.server.Data.Dominio.SesionEntrenamientoDO;

import org.strava.server.Data.Dominio.TokenDO;

import java.util.*;

public class SesionEntrenamientoService {
    private static SesionEntrenamientoService instance;
    private final Map<Long, SesionEntrenamientoDO> sesionesEntrenamiento = new HashMap<>();

    private SesionEntrenamientoService() {
    }

    public static SesionEntrenamientoService getInstance() {
        if(instance == null) {
            instance = new SesionEntrenamientoService();
        }
        return instance;
    }

    public void crearSesionEntrenamiento(TokenDO tokenDo, SesionEntrenamientoDO sesionEntrenamientoDo) throws Exception {
        if(AuthService.getInstance().isTokenValido(tokenDo)) {
            sesionEntrenamientoDo.id = Long.valueOf(sesionesEntrenamiento.size());
            sesionesEntrenamiento.put(sesionEntrenamientoDo.id, sesionEntrenamientoDo);
            System.out.println("Sesion de entrenamiento creada: " + sesionEntrenamientoDo.titulo + " | " + sesionEntrenamientoDo.id.toString());
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
}
