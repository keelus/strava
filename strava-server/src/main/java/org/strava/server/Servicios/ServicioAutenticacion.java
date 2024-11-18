package org.strava.server.Servicios;

import org.strava.server.Data.Dominio.LoginCredencialesDO;
import org.strava.server.Data.Dominio.TokenDO;
import org.strava.server.Data.Dominio.UsuarioDO;
import org.strava.server.Data.Dominio.UsuarioNuevoDO;

import java.util.*;

public class ServicioAutenticacion {
    private static ServicioAutenticacion instance;
    private final List<UsuarioDO> usuarios = new ArrayList<>();
    private final Map<TokenDO, UsuarioDO> sesiones = new HashMap<>();

    private ServicioAutenticacion() {
    }

    public static ServicioAutenticacion getInstance() {
        if(instance == null) {
            instance = new ServicioAutenticacion();
        }
        return instance;
    }

    public UsuarioDO conseguirUsuario(Long usuarioId) throws Exception {
        for(UsuarioDO usuarioDo : usuarios) {
            if(usuarioDo.getId().equals(usuarioId))
                return usuarioDo;
        }
        throw new Exception("No se han encontrado usuarios con esa ID.");
    }

    public void registrarUsuario(UsuarioNuevoDO nuevoUsuarioDo) throws  Exception {
        for(UsuarioDO usuarioDo: usuarios) {
            if(usuarioDo.getEmail().equalsIgnoreCase(nuevoUsuarioDo.getEmail())) {
                throw new Exception("Ya existe un usuario con ese correo");
            }
        }

        usuarios.add(new UsuarioDO(nuevoUsuarioDo, Long.valueOf(usuarios.size())));
    }

    public Optional<TokenDO> crearSesion(LoginCredencialesDO credencialesDo) {
        for(UsuarioDO usuarioDo : usuarios) {
            if(usuarioDo.getEmail().equalsIgnoreCase(credencialesDo.getEmail())) {
                // Autentificacion de Meta/Google

                // Generar token y crear sesion
                TokenDO token = new TokenDO("token aleatorio aqui");
                sesiones.put(token, usuarioDo);

                return Optional.of(token);
            }
        }

        return Optional.empty();
    }

    public void borrarSesion(TokenDO tokenDo) {
        if(sesiones.containsKey(tokenDo)) {
            sesiones.remove(tokenDo);
        }
    }

    public boolean isTokenValido(TokenDO tokenDo) {
        if(sesiones.containsKey(tokenDo)) {
            return true;
        }
        return false;
    }

    public UsuarioDO conseguirUsuarioDeToken(TokenDO tokenDo) throws  Exception {
        if(sesiones.containsKey(tokenDo)) {
            return sesiones.get(tokenDo);
        }
        throw new Exception("El token no es valido o no existe.");
    }
}
