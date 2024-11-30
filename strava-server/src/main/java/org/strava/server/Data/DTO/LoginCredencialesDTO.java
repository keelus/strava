package org.strava.server.Data.DTO;

import org.strava.server.Data.Enums.MetodoRegistro;

import java.io.Serializable;

public class LoginCredencialesDTO implements Serializable {
    private String email;
    private String contrasenya;
    private MetodoRegistro metodoRegistro;

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

    public MetodoRegistro getMetodoRegistro() {
        return metodoRegistro;
    }

    public void setMetodoRegistro(MetodoRegistro metodoRegistro) {
        this.metodoRegistro = metodoRegistro;
    }
}
