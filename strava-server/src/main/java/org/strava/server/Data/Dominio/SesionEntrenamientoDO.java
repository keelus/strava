package org.strava.server.Data.Dominio;

import org.strava.server.Data.Enums.Deporte;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Entity
public class SesionEntrenamientoDO {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private UsuarioDO autor;

    private String titulo;
    private float distanciaKm;
    private Date fechaInicio;
    private Duration duracion;
    private Deporte deporte;

    public SesionEntrenamientoDO() {}

    public SesionEntrenamientoDO(SesionEntrenamientoNuevoDO sesionEntrenamientoNuevoDo, UsuarioDO autor) {
        this.autor = autor;
        this.titulo = sesionEntrenamientoNuevoDo.getTitulo();
        this.distanciaKm = sesionEntrenamientoNuevoDo.getDistanciaKm();
        this.duracion = sesionEntrenamientoNuevoDo.getDuracion();
        this.deporte = sesionEntrenamientoNuevoDo.getDeporte();
        this.fechaInicio = sesionEntrenamientoNuevoDo.getFechaInicio();
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

    public UsuarioDO getAutor() {
        return autor;
    }

    public void setAutor(UsuarioDO autor) {
        this.autor = autor;
    }
}
