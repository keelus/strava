package org.strava.server.Data.Dominio;

import org.strava.server.Data.Enums.MetodoRegistro;

import java.util.Date;

public class UsuarioDO {
    private Long id;
    private String email;
    private String nombre;
    private MetodoRegistro metodoRegistro;
    private Date fechaNacimiento;
    private Double pesoKg;
    private Double alturaCm;
    private Integer frecuenciaCardiacaMax;
    private Integer frecuenciaCardiacaReposo;

    public UsuarioDO() {}

    public UsuarioDO(UsuarioNuevoDO usuarioNuevoDo, Long usuarioId) {
        this.id = usuarioId;
        this.email = usuarioNuevoDo.getEmail();
        this.nombre = usuarioNuevoDo.getEmail();
        this.metodoRegistro = usuarioNuevoDo.getMetodoRegistro();
        this.fechaNacimiento = usuarioNuevoDo.getFechaNacimiento();
        this.pesoKg = usuarioNuevoDo.getPesoKg();
        this.alturaCm = usuarioNuevoDo.getAlturaCm();
        this.frecuenciaCardiacaMax = usuarioNuevoDo.getFrecuenciaCardiacaMax();
        this.frecuenciaCardiacaReposo = usuarioNuevoDo.getFrecuenciaCardiacaReposo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MetodoRegistro getMetodoRegistro() {
        return metodoRegistro;
    }

    public void setMetodoRegistro(MetodoRegistro metodoRegistro) {
        this.metodoRegistro = metodoRegistro;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public Double getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(Double alturaCm) {
        this.alturaCm = alturaCm;
    }

    public Integer getFrecuenciaCardiacaMax() {
        return frecuenciaCardiacaMax;
    }

    public void setFrecuenciaCardiacaMax(Integer frecuenciaCardiacaMax) {
        this.frecuenciaCardiacaMax = frecuenciaCardiacaMax;
    }

    public Integer getFrecuenciaCardiacaReposo() {
        return frecuenciaCardiacaReposo;
    }

    public void setFrecuenciaCardiacaReposo(Integer frecuenciaCardiacaReposo) {
        this.frecuenciaCardiacaReposo = frecuenciaCardiacaReposo;
    }
}
