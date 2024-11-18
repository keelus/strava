package org.strava.server.Servicios;

import org.strava.server.Data.Dominio.RetoDO;
import org.strava.server.Data.Dominio.RetoNuevoDO;
import org.strava.server.Data.Dominio.TokenDO;
import org.strava.server.Data.Dominio.UsuarioDO;

import java.util.*;

public class ServicioReto {
    private static ServicioReto instance;
    // Long llave -> ID del reto, para rapido acceso
    private final Map<Long, RetoDO> retos = new HashMap<>();

    // Long llave -> ID del usuario
    // Long en lista -> ID del reto
    private final Map<Long, List<Long>> retosAceptados = new HashMap<>();

    private ServicioReto() {
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

            RetoDO retoDo = new RetoDO(retoNuevoDo, Long.valueOf(retos.size()));
            retoDo.setAutorId(usuarioDo.getId());

            retos.put(retoDo.getId(), retoDo);
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
    public void aceptarReto(TokenDO tokenDo, Long retoId) throws Exception {
        // Comprobar si el token es valido
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            boolean retoExiste = retos.containsKey(retoId);

            if(retoExiste) {
                UsuarioDO usuarioDo = ServicioAutenticacion.getInstance().conseguirUsuarioDeToken(tokenDo);

                List<Long> retosAceptadosPorElUsuario;
                if(retosAceptados.containsKey(usuarioDo.getId())) {
                    retosAceptadosPorElUsuario = retosAceptados.get(usuarioDo.getId());
                } else {
                    retosAceptadosPorElUsuario = new ArrayList<>();
                }

                retosAceptadosPorElUsuario.add(retoId);
                retosAceptados.put(usuarioDo.getId(), retosAceptadosPorElUsuario);
            } else {
                throw new Exception("El reto no existe.");
            }
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }

    public List<RetoDO> getRetosActivos(TokenDO tokenDo, Date fechaLimite) throws Exception {
        if (ServicioAutenticacion.getInstance().isTokenValido(tokenDo)) {
            List<RetoDO> retosActivos = new ArrayList<>();
            for(RetoDO reto: retos.values()) {
                //if(reto.getFechaFin().before(fechaLimite)) {
                    retosActivos.add(reto);
                //}
            }
            return retosActivos;
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
}
