package com.google.server;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String email;
    private String contrasenya;

    protected Usuario(){}

    public Usuario(String email, String contrasenya) {
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getContrasenya() {
        return contrasenya;
    }
}

interface UsuarioRepositorio extends Repository<Usuario, Long> {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(long id);
}