package org.example.implementaciones;

import org.example.interfaces.ServicioUsuario;
import org.example.modelos.LoginCredenciales;
import org.example.modelos.SesionEntrenamiento;
import org.example.modelos.Usuario;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ServicioUsuarioImpl extends UnicastRemoteObject implements ServicioUsuario {

    public ServicioUsuarioImpl() throws RemoteException {
        super();
    }

    @Override
    public Usuario login(LoginCredenciales credenciales) throws RemoteException {
        if (credenciales.getEmail().equals("test") && credenciales.getContrasenya().equals("test")) {
            Usuario usuario = new Usuario();
            usuario.setEmail(credenciales.getEmail());
            usuario.setNombre("test");
            return usuario;
        }
        return null;
    }

    @Override
    public String registrar(Usuario usuario) throws RemoteException {
        return "Usuario registrado: " + usuario.getEmail();
    }
}