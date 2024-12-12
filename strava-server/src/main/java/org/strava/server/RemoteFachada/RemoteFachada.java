package org.strava.server.RemoteFachada;

import org.strava.server.Data.DTO.*;
import org.strava.server.Data.DTO.Assemblers.*;
import org.strava.server.Data.Dominio.*;
import org.strava.server.Data.Enums.MetodoRegistro;
import org.strava.server.Data.Enums.TipoObjetivo;
import org.strava.server.Servicios.ServicioAutenticacion;
import org.strava.server.Servicios.ServicioReto;
import org.strava.server.Servicios.ServicioSesionEntrenamiento;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RemoteFachada extends UnicastRemoteObject implements IRemoteFachada {
    private final ServicioAutenticacion servicioAutenticacion = ServicioAutenticacion.getInstance();
    private final ServicioReto servicioReto = ServicioReto.getInstance();
    private final ServicioSesionEntrenamiento servicioSesionEntrenamiento = ServicioSesionEntrenamiento.getInstance();

    public RemoteFachada() throws  RemoteException {
        super();
    }
    // AUTH
    @Override
    public void authRegistrarGoogle(DatosRegistroDTO datosRegistroDto) throws RemoteException {
        try {
            servicioAutenticacion.registrarUsuario(DatosRegistroAssembler.dtoToDo(datosRegistroDto));
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
    @Override
    public void authRegistrarMeta(DatosRegistroDTO datosRegistroDto) throws RemoteException {
        try {
            servicioAutenticacion.registrarUsuario(DatosRegistroAssembler.dtoToDo(datosRegistroDto));
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public TokenDTO authLoginGoogle(LoginCredencialesDTO credencialesDto) throws RemoteException {
        try {
            TokenDO token = servicioAutenticacion.crearSesion(LoginCredencialesAssembler.dtoToDo(credencialesDto), MetodoRegistro.Google);
            return TokenAssembler.doToDto(token);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public TokenDTO authLoginMeta(LoginCredencialesDTO credencialesDto) throws RemoteException {
        try {
            TokenDO token = servicioAutenticacion.crearSesion(LoginCredencialesAssembler.dtoToDo(credencialesDto), MetodoRegistro.Meta);
            return TokenAssembler.doToDto(token);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void authLogout(TokenDTO tokenDto) throws RemoteException{
        servicioAutenticacion.borrarSesion(TokenAssembler.dtoToDo(tokenDto));
    }

    // RETO
    @Override
    public void retoCrear(TokenDTO tokenDto, RetoNuevoDTO retoNuevoDto) throws RemoteException{
        try {
            System.out.println("== INFO RETO==");
            System.out.println(retoNuevoDto.getNombre());
            System.out.println(retoNuevoDto.getFechaInicio());
            System.out.println(retoNuevoDto.getFechaFin());
            System.out.println(retoNuevoDto.getValorObjetivo());
            System.out.println(retoNuevoDto.getTipoObjetivo());
            System.out.println(retoNuevoDto.getDeporte());
            servicioReto.crearReto(TokenAssembler.dtoToDo(tokenDto), RetoNuevoAssembler.dtoToDo(retoNuevoDto));
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void retoAceptar(TokenDTO tokenDto, RetoDTO retoDto) throws RemoteException{
        try {
            servicioReto.aceptarReto(TokenAssembler.dtoToDo(tokenDto), retoDto.getId());
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List<RetoDTO> retoListarActivos(TokenDTO tokenDto, Date fechaLimite) throws RemoteException {
        TokenDO tokenDo = TokenAssembler.dtoToDo(tokenDto);

        try {
            List<RetoDO> retosActivosDO = servicioReto.getRetosActivos(tokenDo, fechaLimite);
            List<RetoDTO> retosActivos = new ArrayList<>();
            for(RetoDO retoActivoDo : retosActivosDO) {
                boolean retoAceptadoPorUsuario = servicioReto.isRetoAceptadoPorUsuario(tokenDo, retoActivoDo.getId());
                retosActivos.add(RetoAssembler.doToDto(retoActivoDo, retoAceptadoPorUsuario));
            }
            return retosActivos;
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public List<RetoDTO> retoListarAceptados(TokenDTO tokenDto) throws RemoteException {
        TokenDO tokenDo = TokenAssembler.dtoToDo(tokenDto);

        try {
            List<RetoDO> retosAceptadosDO = servicioReto.getRetosAceptados(tokenDo);
            List<SesionEntrenamientoDO> sesionesDo = servicioSesionEntrenamiento.getSesionesEntrenamientos(tokenDo);
            List<RetoDTO> retosAceptados = new ArrayList<>();
            for(RetoDO retoActivoDo : retosAceptadosDO) {
                RetoDTO retoDto = RetoAssembler.doToDto(retoActivoDo, true);
                float objetivo = retoDto.getValorObjetivo();
                float sumaObjetivo = 0;


                for(SesionEntrenamientoDO sesionDo: sesionesDo) {
                    if(retoDto.getDeporte().contains(sesionDo.getDeporte())) {
                        if (sesionDo.getFechaInicio().after(retoDto.getFechaFin()) ||
                                sesionDo.getFechaInicio().before(retoDto.getFechaInicio())) {
                            continue;
                        }

                        if (retoDto.getTipoObjetivo().equals(TipoObjetivo.Distancia)) {
                            sumaObjetivo += sesionDo.getDistanciaKm();
                        } else {
                            sumaObjetivo += sesionDo.getDuracion().toMinutes();
                        }
                    }
                }

                // Sobre 100
                float porcentajeCompletado = sumaObjetivo * 100.0f / objetivo;
                if(porcentajeCompletado < 0.00f) porcentajeCompletado = 0.00f;
                else if(porcentajeCompletado >= 100.00f) porcentajeCompletado = 100.00f;

                retoDto.setPorcentajeCompletado(porcentajeCompletado);
                retosAceptados.add(retoDto);
            }
            return retosAceptados;
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    // SESION ENTRAMIENTO
    @Override
    public void sesionEntrenamientoCrear(TokenDTO tokenDto, SesionEntrenamientoNuevoDTO sesionEntrenamientoNuevoDto) throws RemoteException{
        TokenDO tokenDo = TokenAssembler.dtoToDo(tokenDto);
        SesionEntrenamientoNuevoDO sesionEntrenamientoNuevoDo = SesionEntrenamientoNuevoAssembler.dtoToDo(sesionEntrenamientoNuevoDto);
        try {
            servicioSesionEntrenamiento.crearSesionEntrenamiento(tokenDo, sesionEntrenamientoNuevoDo);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List<SesionEntrenamientoDTO> sesionEntrenamientoListar(TokenDTO tokenDto) throws RemoteException {
        TokenDO tokenDo = TokenAssembler.dtoToDo(tokenDto);

        try {
            List<SesionEntrenamientoDO> sesionesEntrenamientoDo = servicioSesionEntrenamiento.getSesionesEntrenamientos(tokenDo);
            List<SesionEntrenamientoDTO> sesionesEntrenamiento = new ArrayList<>();
            for(SesionEntrenamientoDO sesionEntrenamientoDo: sesionesEntrenamientoDo) {
                sesionesEntrenamiento.add(SesionEntrenamientoAssembler.doToDto(sesionEntrenamientoDo));
            }
            return sesionesEntrenamiento;
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public UsuarioDTO conseguirUsuarioDeToken(TokenDTO tokenDto) throws  RemoteException {
        TokenDO tokenDo = TokenAssembler.dtoToDo(tokenDto);

        try {
            UsuarioDO usuarioDo = servicioAutenticacion.conseguirUsuarioDeToken(tokenDo);
            return UsuarioAssembler.doToDto(usuarioDo);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
