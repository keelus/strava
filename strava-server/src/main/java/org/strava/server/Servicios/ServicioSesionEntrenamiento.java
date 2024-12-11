package org.strava.server.Servicios;

import org.strava.server.Data.DTO.SesionEntrenamientoDTO;
import org.strava.server.Data.Dominio.*;

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
            UsuarioDO usuarioDo = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);
            SesionEntrenamientoDO sesionEntrenamientoDo = new SesionEntrenamientoDO(sesionEntrenamientoNuevoDo, Long.valueOf(sesionesEntrenamiento.size()), usuarioDo.getId());
            sesionesEntrenamiento.put(sesionEntrenamientoDo.getId(), sesionEntrenamientoDo);
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }

    public List<SesionEntrenamientoDO> getSesionesEntrenamientos(TokenDO tokenDo) throws Exception {
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            List<SesionEntrenamientoDO> sesionesEntrenamientosDelUsuario = new ArrayList<>();
            UsuarioDO usuarioDo = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);
            for(Map.Entry<Long, SesionEntrenamientoDO> entry: sesionesEntrenamiento.entrySet()) {
                SesionEntrenamientoDO sesionEntrenamientoDo = entry.getValue();
                if(sesionEntrenamientoDo.getAutorId().equals(usuarioDo.getId())) {
                    sesionesEntrenamientosDelUsuario.add(sesionEntrenamientoDo);
                }
            }

            return sesionesEntrenamientosDelUsuario;
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
}
