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
        this.contrasenya = Crypto.EncriptarConMd5(contrasenya);
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
    Optional<Usuario> getByEmail(String email);

    @Transactional
    default Usuario updateOrInsert(Usuario usuario) {
        return save(usuario);
    }
}

class UsuarioHttp {
    private String email;
    private String contrasenya;

    public UsuarioHttp() {}

    public UsuarioHttp(String email, String contrasenya) {
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public UsuarioHttp(Usuario usuario) {
        this.email = usuario.getEmail();
        this.contrasenya = usuario.getContrasenya();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String toString() {
        return "{" + email + ";" + contrasenya + "}";
    }
}