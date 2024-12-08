package com.meta;

import java.util.concurrent.ConcurrentHashMap;

public class PersistenciaUsuario {
    private final ConcurrentHashMap<String, String> usuarios;

    public PersistenciaUsuario() {
        this.usuarios = new ConcurrentHashMap<>();
    }

    public synchronized void anadirUsuario(String email, String contrasena) {
        if (usuarios.containsKey(email)) {
            System.out.println("El usuario ya existe: " + email);
            return;
        }
        usuarios.put(email, contrasena);
        System.out.println("Usuario añadido: " + email);
    }

    public synchronized boolean existeUsuario(String email) {
        return usuarios.containsKey(email);
    }

    public synchronized String getContrasena(String email) {
        return usuarios.get(email);
    }
}
