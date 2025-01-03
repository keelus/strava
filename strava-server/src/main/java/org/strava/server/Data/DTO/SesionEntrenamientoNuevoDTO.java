package org.strava.server.Data.DTO;

import org.strava.server.Data.Enums.Deporte;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

public class SesionEntrenamientoNuevoDTO implements Serializable {
    private String titulo;
    private float distanciaKm;
    private Date fechaInicio;
    private Duration duracion;
    private Deporte deporte;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(float distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }
}
