package org.example.interfaces;

import org.example.modelos.LoginCredenciales;
import org.example.modelos.SesionEntrenamiento;
import org.example.modelos.Usuario;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioUsuario extends Remote {
    Usuario login(LoginCredenciales credenciales) throws RemoteException;
    String registrar(Usuario usuario) throws RemoteException;
}
