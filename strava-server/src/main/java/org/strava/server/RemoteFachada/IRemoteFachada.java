package org.strava.server.RemoteFachada;

import org.strava.server.Data.DTO.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IRemoteFachada extends Remote {
    void authRegistrar(UsuarioNuevoDTO usuarioNuevoDto) throws RemoteException;
    TokenDTO authLogin(LoginCredencialesDTO credencialesDto) throws RemoteException;
    void authLogout(TokenDTO tokenDto) throws RemoteException;

    void retoCrear(TokenDTO tokenDto, RetoNuevoDTO retoNuevoDto) throws RemoteException;
    void retoAceptar(TokenDTO tokenDto, Long retoId) throws RemoteException;
    List<RetoDTO> retoListarActivos(TokenDTO tokenDto, Date fechaLimite) throws RemoteException;

    void sesionEntrenamientoCrear(TokenDTO tokenDto, SesionEntrenamientoNuevoDTO sesionEntrenamientoNuevoDto) throws RemoteException;
}
