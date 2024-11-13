package org.example.modelos;

import java.io.Serializable;

public class RetoAceptarCuerpo implements Serializable {
    public Reto reto;
    public Usuario usuario;

    public Reto getReto() {
        return reto;
    }

    public void setReto(Reto reto) {
        this.reto = reto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
