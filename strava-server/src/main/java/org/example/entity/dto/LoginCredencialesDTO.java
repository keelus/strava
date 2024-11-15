package org.example.entity.dto;

import java.io.Serializable;

public class LoginCredencialesDTO implements Serializable {
    private String email;
    private String contrasenya;

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
