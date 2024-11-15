package org.example.service;

import org.example.entity.domain.LoginCredencialesDO;
import org.example.entity.domain.TokenDO;
import org.example.entity.domain.UsuarioDO;

import java.util.*;

public class AuthService {
    private final List<UsuarioDO> usuarios = new ArrayList<>();
    private final Map<TokenDO, UsuarioDO> sesiones = new HashMap<>();

    public AuthService() {
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
}
