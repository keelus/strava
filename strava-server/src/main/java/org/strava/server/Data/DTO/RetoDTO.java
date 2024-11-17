package org.strava.server.Data.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class RetoDTO implements Serializable  {
    public Long id;
    public String nombre;
    public UsuarioDTO autor; // Esto se podria quitar? O en DO? Mirarlo
    public String fechaInicio;
    public String fechaFin;
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

    public UsuarioDTO getAutor() {
        return autor;
    }

    public void setAutor(UsuarioDTO autor) {
        this.autor = autor;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
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
