package org.example.modelos;

import java.io.Serializable;

public class LoginCredenciales implements Serializable {
    public String email;
    public String contrasenya;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}