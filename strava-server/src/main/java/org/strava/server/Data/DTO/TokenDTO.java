package org.strava.server.Data.DTO;

import java.io.Serializable;

public class TokenDTO implements Serializable {
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
