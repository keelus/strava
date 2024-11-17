package org.strava.server.Data.Dominio;

import java.util.ArrayList;
import java.util.Date;

public class RetoDO {
    public Long id;
    public String nombre;
    public Long autorId;
    public Date fechaInicio;
    public Date fechaFin;
    public String tipoObjetivo;
    public int objetivo;
    public ArrayList<String> deporte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoObjetivo() {
        return tipoObjetivo;
    }

    public void setTipoObjetivo(String tipoObjetivo) {
        this.tipoObjetivo = tipoObjetivo;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    public ArrayList<String> getDeporte() {
        return deporte;
    }

    public void setDeporte(ArrayList<String> deporte) {
        this.deporte = deporte;
    }
}
