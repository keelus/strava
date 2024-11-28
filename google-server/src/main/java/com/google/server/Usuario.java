package com.google.server;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
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

    public String toString() {
        return "{" + id + ";" + email + ";" + contrasenya + "}";
    }
}

interface UsuarioRepositorio extends Repository<Usuario, Long> {
    Usuario save(Usuario usuario);
    Optional<Usuario> getById(long id);
    Iterable<Usuario> findAll();

    @Transactional
    default Usuario updateOrInsert(Usuario usuario) {
        return save(usuario);
    }
}