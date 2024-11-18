package org.strava.server.Data.Dominio;

import org.strava.server.Data.Enums.Deporte;
import org.strava.server.Data.Enums.TipoObjetivo;

import java.util.ArrayList;
import java.util.Date;

public class RetoNuevoDO {
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private TipoObjetivo tipoObjetivo;
    private float valorObjetivo;
    private ArrayList<Deporte> deporte;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
