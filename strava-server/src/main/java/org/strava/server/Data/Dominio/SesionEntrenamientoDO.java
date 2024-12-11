package org.strava.server.Data.Dominio;

import org.strava.server.Data.Enums.Deporte;

import java.time.Duration;
import java.util.Date;

public class SesionEntrenamientoDO {
    private Long id;
    private Long autorId;
    private String titulo;
    private float distanciaKm;
    private Date fechaInicio;
    private int horaInicio;
    private Duration duracion;
    private Deporte deporte;

    public SesionEntrenamientoDO() {}

    public SesionEntrenamientoDO(SesionEntrenamientoNuevoDO sesionEntrenamientoNuevoDo, Long sesionEntrenamientoId, Long autorId) {
        this.id = sesionEntrenamientoId;
        this.autorId = autorId;
        this.titulo = sesionEntrenamientoNuevoDo.getTitulo();
        this.distanciaKm = sesionEntrenamientoNuevoDo.getDistanciaKm();
        this.fechaInicio = sesionEntrenamientoNuevoDo.getFechaInicio();
        this.horaInicio = sesionEntrenamientoNuevoDo.getHoraInicio();
        this.duracion = sesionEntrenamientoNuevoDo.getDuracion();
        this.deporte = sesionEntrenamientoNuevoDo.getDeporte();
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

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }
}
