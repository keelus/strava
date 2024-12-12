package com.meta;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String email;
    private String contrasena;

    public Usuario(String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario(String contenido) {
        String[] contenidoSplit = contenido.split(";");
        this.email = contenidoSplit[0];
        this.contrasena = contenidoSplit[1];
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return email + ";" + contrasena;
    }
}
