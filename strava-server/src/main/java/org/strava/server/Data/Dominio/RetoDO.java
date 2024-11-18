package org.strava.server.Data.Dominio;

import org.strava.server.Data.Enums.Deporte;
import org.strava.server.Data.Enums.TipoObjetivo;

import java.util.ArrayList;
import java.util.Date;

public class RetoDO {
    private Long id;
    private String nombre;
    private Long autorId;
    private Date fechaInicio;
    private Date fechaFin;
    private TipoObjetivo tipoObjetivo;
    private float valorObjetivo;
    private ArrayList<Deporte> deporte;

    public RetoDO(){}

    public RetoDO(RetoNuevoDO retoNuevoDo, Long retoId) {
        this.id = retoId;
        this.nombre = retoNuevoDo.getNombre();
        //this.autorId = retoNuevoDo.ge();
        this.fechaInicio = retoNuevoDo.getFechaInicio();
        this.fechaFin = retoNuevoDo.getFechaFin();
        this.tipoObjetivo = retoNuevoDo.getTipoObjetivo();
        this.valorObjetivo = retoNuevoDo.getValorObjetivo();
        this.deporte = retoNuevoDo.getDeporte();
    }

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

    public TipoObjetivo getTipoObjetivo() {
        return tipoObjetivo;
    }

    public void setTipoObjetivo(TipoObjetivo tipoObjetivo) {
        this.tipoObjetivo = tipoObjetivo;
    }

    public float getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(float valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public ArrayList<Deporte> getDeporte() {
        return deporte;
    }

    public void setDeporte(ArrayList<Deporte> deporte) {
        this.deporte = deporte;
    }
}
