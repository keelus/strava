package org.example.service;

import org.example.entity.assembler.RetoAssembler;
import org.example.entity.domain.RetoDO;
import org.example.entity.domain.TokenDO;
import org.example.entity.dto.RetoDTO;
import org.example.entity.dto.TokenDTO;
import org.example.facade.implementaciones.AuthController;

import java.rmi.RemoteException;
import java.util.*;

public class RetoService {
    private static RetoService instance;

    private final Map<Long, RetoDO> retos = new HashMap<>();
    // TODO: Retos aceptados?

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
            System.out.println("Reto creado: " + retoDo.nombre + " | " + retoDo.id.toString());
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
    public void aceptarReto(TokenDO tokenDo, Long retoId) throws Exception {
        // Comprobar si el token es valido
        if (AuthService.getInstance().isTokenValido(tokenDo)) {
            // TODO
            // retos.forEach(reto -> );
            // retoDo.id = Long.valueOf(retos.size());
            // retos.put(retoDo.id, retoDo);
            // System.out.println("Reto creado: " + retoDo.nombre + " | " + retoDo.id.toString());
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }

    public List<RetoDO> getRetosActivos(TokenDO tokenDo, Date fechaLimite) throws Exception {
        if (AuthService.getInstance().isTokenValido(tokenDo)) {
            // TODO
            // retos.forEach(reto -> );
            // retoDo.id = Long.valueOf(retos.size());
            // retos.put(retoDo.id, retoDo);
            // System.out.println("Reto creado: " + retoDo.nombre + " | " + retoDo.id.toString());
            return new ArrayList<>();
        } else {
            throw new Exception("El token de sesion no es valido.");
        }
    }
}
