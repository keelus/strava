package org.strava.server.Data.Dominio;

import java.time.Duration;
import java.util.Date;

public class SesionEntrenamientoDO {
    private Long id;
    private String titulo;
    private float distanciaKm;
    private Date fechaInicio;
    private int horaInicio;
    private Duration duracion;

    public SesionEntrenamientoDO() {}

    public SesionEntrenamientoDO(SesionEntrenamientoNuevoDO sesionEntrenamientoNuevoDo, Long sesionEntrenamientoId) {
        this.id = sesionEntrenamientoId;
        this.titulo = sesionEntrenamientoNuevoDo.getTitulo();
        this.distanciaKm = sesionEntrenamientoNuevoDo.getDistanciaKm();
        this.fechaInicio = sesionEntrenamientoNuevoDo.getFechaInicio();
        this.horaInicio = sesionEntrenamientoNuevoDo.getHoraInicio();
        this.duracion = sesionEntrenamientoNuevoDo.getDuracion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }
}
