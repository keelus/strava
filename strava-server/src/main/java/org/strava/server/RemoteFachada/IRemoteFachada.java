package org.strava.server.RemoteFachada;

import org.strava.server.Data.DTO.UsuarioDTO;
import org.strava.server.Data.DTO.LoginCredencialesDTO;
import org.strava.server.Data.DTO.TokenDTO;
import org.strava.server.Data.DTO.RetoDTO;
import org.strava.server.Data.DTO.SesionEntrenamientoDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IRemoteFachada extends Remote {
    void authRegistrar(UsuarioDTO usuarioDto) throws RemoteException;
    TokenDTO authLogin(LoginCredencialesDTO credencialesDto) throws RemoteException;
    void authLogout(TokenDTO tokenDto) throws RemoteException;

    void retoCrear(TokenDTO tokenDto, RetoDTO retoDto) throws RemoteException;
    void retoAceptar(TokenDTO tokenDto, Long retoId) throws RemoteException;
    List<RetoDTO> retoListarActivos(TokenDTO tokenDto, Date fechaLimite) throws RemoteException;

    void sesionEntrenamientoCrear(TokenDTO tokenDto, SesionEntrenamientoDTO sesionEntrenamientoDto) throws RemoteException;
}
