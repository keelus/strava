package org.strava.cliente;

import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;
import org.strava.server.Data.DTO.*;
import org.strava.server.RemoteFachada.IRemoteFachada;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class Controlador {
    private static Controlador instance;
    private IRemoteFachada remoteFachada;
    private TokenDTO tokenSesion;

    private Controlador() {
        this.remoteFachada = Localizador.getRemoteFachada();
        tokenSesion = null;
    }

    public static Controlador getInstance() {
        if(instance == null) {
            instance = new Controlador();
        }
        return instance;
    }

    public void iniciarSesion(String email, char[] contrasenya, FormularioExternoServicio servicio) throws RemoteException {
        LoginCredencialesDTO credenciales = new LoginCredencialesDTO();
        credenciales.setEmail(email);
        credenciales.setContrasenya(new String(contrasenya));

        this.tokenSesion = servicio == FormularioExternoServicio.GOOGLE
                ? remoteFachada.authLoginGoogle(credenciales)
                : remoteFachada.authLoginMeta(credenciales);
    }


    public void registrarUsuario(DatosRegistroDTO datosRegistroDto, FormularioExternoServicio servicio) throws RemoteException {
        if(servicio == FormularioExternoServicio.GOOGLE)
            remoteFachada.authRegistrarGoogle(datosRegistroDto);
        else
            remoteFachada.authRegistrarMeta(datosRegistroDto);
    }

    public void crearReto(RetoNuevoDTO reto) throws RemoteException {
        remoteFachada.retoCrear(this.tokenSesion, reto);
    }

    public List<RetoDTO> conseguirRetosActivos(Date fechaLimite) throws RemoteException {
        return remoteFachada.retoListarActivos(this.tokenSesion, fechaLimite);
    }
}
