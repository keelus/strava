package org.example.modelos;

import java.io.Serializable;

public class RetoListarActivosCuerpo implements Serializable {
    public String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
