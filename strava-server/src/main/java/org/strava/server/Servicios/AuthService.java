package org.strava.server.Servicios;

import org.strava.server.Data.Dominio.LoginCredencialesDO;
import org.strava.server.Data.Dominio.TokenDO;
import org.strava.server.Data.Dominio.UsuarioDO;

import java.util.*;

public class AuthService {
    private static AuthService instance;
    private final List<UsuarioDO> usuarios = new ArrayList<>();
    private final Map<TokenDO, UsuarioDO> sesiones = new HashMap<>();

    private AuthService() {
    }

    public static AuthService getInstance() {
        if(instance == null) {
            instance = new AuthService();
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

    public void registrarUsuario(UsuarioDO nuevoUsuarioDo) throws  Exception {
        for(UsuarioDO usuarioDo: usuarios) {
            if(usuarioDo.getEmail().equalsIgnoreCase(nuevoUsuarioDo.getEmail())) {
                throw new Exception("Ya existe un usuario con ese correo");
            }
        }

        usuarios.add(nuevoUsuarioDo);
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
        for(TokenDO tokenSesDo: this.sesiones.keySet()) {
            System.out.println(tokenSesDo.getValor());
        }
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
