package org.strava.server.Data.Dominio;

import org.strava.server.Data.Enums.MetodoRegistro;

public class LoginCredencialesDO {
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
