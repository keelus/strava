package org.strava.server.RemoteFachada;

import org.strava.server.Data.DTO.*;
import org.strava.server.Data.Dominio.TokenDO;
import org.strava.server.Data.Dominio.UsuarioDO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IRemoteFachada extends Remote {
    // Realizan el login, y si va OK, devuelven el token
    TokenDTO authLoginGoogle(LoginCredencialesDTO credencialesDto) throws RemoteException;
    TokenDTO authLoginMeta(LoginCredencialesDTO credencialesDto) throws RemoteException;

    // Realizan el registro, no sin antes verificar las credenciales con Google o Meta.
    // Tambien, verifican que el email no exista y que los datos sean validos. Si son correctos, devuelve OK.
    void authRegistrarGoogle(DatosRegistroDTO datosRegistroDto) throws RemoteException;
    void authRegistrarMeta(DatosRegistroDTO datosRegistroDto) throws RemoteException;

    void authLogout(TokenDTO tokenDto) throws RemoteException;

    void retoCrear(TokenDTO tokenDto, RetoNuevoDTO retoNuevoDto) throws RemoteException;
    void retoAceptar(TokenDTO tokenDto, RetoDTO retoDto) throws RemoteException;
    List<RetoDTO> retoListarActivos(TokenDTO tokenDto, Date fechaLimite) throws RemoteException;
    List<RetoDTO> retoListarAceptados(TokenDTO tokenDto) throws RemoteException;

    void sesionEntrenamientoCrear(TokenDTO tokenDto, SesionEntrenamientoNuevoDTO sesionEntrenamientoNuevoDto) throws RemoteException;
    List<SesionEntrenamientoDTO> sesionEntrenamientoListar(TokenDTO tokenDto) throws RemoteException;

    UsuarioDTO conseguirUsuarioDeToken(TokenDTO tokenDto) throws  RemoteException;
}
