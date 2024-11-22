package org.strava.cliente.gui.FormularioExterno.LoginParaRegistro;

import java.util.Date;

public class DatosRegistro {
    String email;
    String nombre;
    Date fechaNacimiento;
    float pesoKg;
    float alturaCm;
    int freqMax;
    int freqReposo;

    public DatosRegistro() {

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public float getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(float pesoKg) {
        this.pesoKg = pesoKg;
    }

    public float getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(float alturaCm) {
        this.alturaCm = alturaCm;
    }

    public int getFreqMax() {
        return freqMax;
    }

    public void setFreqMax(int freqMax) {
        this.freqMax = freqMax;
    }

    public int getFreqReposo() {
        return freqReposo;
    }

    public void setFreqReposo(int freqReposo) {
        this.freqReposo = freqReposo;
    }
}
