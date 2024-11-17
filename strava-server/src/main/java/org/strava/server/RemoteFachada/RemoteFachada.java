package org.strava.server.RemoteFachada;

import org.strava.server.Data.DTO.*;
import org.strava.server.Data.DTO.Assemblers.*;
import org.strava.server.Data.Dominio.RetoDO;
import org.strava.server.Data.Dominio.SesionEntrenamientoDO;
import org.strava.server.Data.Dominio.TokenDO;
import org.strava.server.Servicios.AuthService;
import org.strava.server.Servicios.RetoService;
import org.strava.server.Servicios.SesionEntrenamientoService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RemoteFachada extends UnicastRemoteObject implements IRemoteFachada {
    private final AuthService authService = AuthService.getInstance();
    private final RetoService retoService = RetoService.getInstance();
    private final SesionEntrenamientoService sesionEntrenamientoService = SesionEntrenamientoService.getInstance();

    public RemoteFachada() throws  RemoteException {
        super();
    }
    // AUTH
    @Override
    public void authRegistrar(UsuarioDTO usuarioDto) throws RemoteException {
        try {
            authService.registrarUsuario(UsuarioAssembler.dtoToDo(usuarioDto));
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public TokenDTO authLogin(LoginCredencialesDTO credencialesDto) throws RemoteException{
        Optional<TokenDO> token = authService.crearSesion(LoginCredencialesAssembler.dtoToDo(credencialesDto));

        return TokenAssembler.doToDto(
                token.orElseThrow(() -> new RemoteException(
                        "Las credenciales son incorrectas o el usuario no existe."
                ))
        );
    }

    @Override
    public void authLogout(TokenDTO tokenDto) throws RemoteException{
        authService.borrarSesion(TokenAssembler.dtoToDo(tokenDto));
    }

    // RETO
    @Override
    public void retoCrear(TokenDTO tokenDto, RetoDTO retoDto) throws RemoteException{
        try {
            System.out.println("== INFO RETO==");
            System.out.println(retoDto.getNombre());
            System.out.println(retoDto.getFechaInicio());
            System.out.println(retoDto.getFechaFin());
            System.out.println(retoDto.getObjetivo());
            System.out.println(retoDto.getTipoObjetivo());
            System.out.println(retoDto.getDeporte());
            retoService.crearReto(TokenAssembler.dtoToDo(tokenDto), RetoAssembler.dtoToDo(retoDto));
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void retoAceptar(TokenDTO tokenDto, Long retoId) throws RemoteException{
        try {
            retoService.aceptarReto(TokenAssembler.dtoToDo(tokenDto), retoId);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List<RetoDTO> retoListarActivos(TokenDTO tokenDto, Date fechaLimite) throws RemoteException{
        TokenDO tokenDo = TokenAssembler.dtoToDo(tokenDto);
        try {
            List<RetoDO> retosActivosDO = retoService.getRetosActivos(tokenDo, fechaLimite);
            List<RetoDTO> retosActivos = new ArrayList<>();
            for(RetoDO retoActivoDo : retosActivosDO) {
                retosActivos.add(RetoAssembler.doToDto(retoActivoDo));
            }
            return retosActivos;
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    // SESION ENTRAMIENTO
    @Override
    public void sesionEntrenamientoCrear(TokenDTO tokenDto, SesionEntrenamientoDTO sesionEntrenamientoDto) throws RemoteException{
        TokenDO tokenDo = TokenAssembler.dtoToDo(tokenDto);
        SesionEntrenamientoDO sesionEntrenamientoDo = SesionEntrenamientoAssembler.dtoToDo(sesionEntrenamientoDto);
        try {
            sesionEntrenamientoService.crearSesionEntrenamiento(tokenDo, sesionEntrenamientoDo);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
