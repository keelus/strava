package org.strava.server.Servicios;

import org.strava.server.Data.Dominio.RetoDO;
import org.strava.server.Data.Dominio.TokenDO;
import org.strava.server.Data.Dominio.UsuarioDO;

import java.util.*;

public class RetoService {
    private static RetoService instance;
    // Long llave -> ID del reto, para rapido acceso
    private final Map<Long, RetoDO> retos = new HashMap<>();

    // Long llave -> ID del usuario
    // Long en lista -> ID del reto
    private final Map<Long, List<Long>> retosAceptados = new HashMap<>();

    private RetoService() {
    }

    public static RetoService getInstance() {
        if(instance == null) {
            instance = new RetoService();
        }
        return instance;
    }

    public void crearReto(TokenDO tokenDo, RetoDO retoDo) throws Exception {
        // Comprobar si el token es valido
        if (AuthService.getInstance().isTokenValido(tokenDo)) {
            retoDo.id = Long.valueOf(retos.size());
            retos.put(retoDo.id, retoDo);
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
    public void aceptarReto(TokenDO tokenDo, Long retoId) throws Exception {
        // Comprobar si el token es valido
        if (AuthService.getInstance().isTokenValido(tokenDo)) {
            boolean retoExiste = retos.containsKey(retoId);

            if(retoExiste) {
                UsuarioDO usuarioDo = AuthService.getInstance().conseguirUsuarioDeToken(tokenDo);

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
        if (AuthService.getInstance().isTokenValido(tokenDo)) {
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
