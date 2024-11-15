package org.example.facade.interfaces;

import org.example.entity.dto.LoginCredencialesDTO;
import org.example.entity.dto.TokenDTO;
import org.example.entity.dto.UsuarioDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuthController extends Remote {
    public void registrar(UsuarioDTO usuarioDto) throws RemoteException;
    public TokenDTO login(LoginCredencialesDTO credencialesDto) throws RemoteException;
    public void logout(TokenDTO tokenDto) throws RemoteException;
}
