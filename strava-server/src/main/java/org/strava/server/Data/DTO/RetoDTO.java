package org.strava.server.Data.DTO;

import org.strava.server.Data.Enums.Deporte;
import org.strava.server.Data.Enums.TipoObjetivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class RetoDTO implements Serializable  {
    private Long id;
    private String nombre;
    private UsuarioDTO autor; // Esto se podria quitar? O en DO? Mirarlo
    private Date fechaInicio;
    private Date fechaFin;
    private TipoObjetivo tipoObjetivo;
    private float valorObjetivo;
    private ArrayList<Deporte> deporte;
    private boolean aceptadoPorUsuario;
    private float porcentajeCompletado;

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

    public UsuarioDTO getAutor() {
        return autor;
    }

    public void setAutor(UsuarioDTO autor) {
        this.autor = autor;
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

    public boolean isAceptadoPorUsuario() {
        return aceptadoPorUsuario;
    }

    public void setAceptadoPorUsuario(boolean aceptadoPorUsuario) {
        this.aceptadoPorUsuario = aceptadoPorUsuario;
    }

    public float getPorcentajeCompletado() {
        return porcentajeCompletado;
    }

    public void setPorcentajeCompletado(float porcentajeCompletado) {
        this.porcentajeCompletado = porcentajeCompletado;
    }
}
