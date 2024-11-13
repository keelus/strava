package org.example.implementaciones;

import org.example.interfaces.ServicioUsuario;
import org.example.modelos.LoginCredenciales;
import org.example.modelos.Usuario;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServicioUsuarioImpl extends UnicastRemoteObject implements ServicioUsuario {
    private ArrayList<Usuario> usuariosRegistrados = new ArrayList<Usuario>(); // Simula la base de datos de usuarios

    public ServicioUsuarioImpl() throws RemoteException {
        super();
    }

    @Override
    public Usuario login(LoginCredenciales credenciales) throws RemoteException {
        for(int i = 0; i < usuariosRegistrados.size(); i++) {
            if(usuariosRegistrados.get(i).getEmail().equals(credenciales.getEmail())) {
                // Aqui iria la verificacion google/meta
                return usuariosRegistrados.get(i);
            }
        }
        throw new RemoteException("Error! Usuario no registrado.");
    }

    @Override
    public String registrar(Usuario usuario) throws RemoteException{
        for(int i = 0; i < usuariosRegistrados.size(); i++) {
            if(usuariosRegistrados.get(i).getEmail().equals(usuario.getEmail())) {
                throw new RemoteException("Error! Usuario ya registrado.");
            }
        }
        usuariosRegistrados.add(usuario);
        return "Usuario registrado correctamente: " + usuario.getEmail();
    }

}