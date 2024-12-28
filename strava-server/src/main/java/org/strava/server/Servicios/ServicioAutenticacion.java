package org.strava.server.Servicios;

import org.strava.server.AutenticacionGateway.AutenticacionGatewayFactory;
import org.strava.server.AutenticacionGateway.IAutenticacionGateway;
import org.strava.server.Data.DAO.UsuarioDAO;
import org.strava.server.Data.Dominio.*;
import org.strava.server.Data.Enums.MetodoRegistro;

import java.util.*;

public class ServicioAutenticacion {
    UsuarioDAO usuarioDAO;

    private static ServicioAutenticacion instance;
    private final Map<TokenDO, Long> sesiones = new HashMap<>();

    private ServicioAutenticacion() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public static ServicioAutenticacion getInstance() {
        if(instance == null) {
            instance = new ServicioAutenticacion();
        }
        return instance;
    }

    public void registrarUsuario(UsuarioDO usuarioNuevo) {
        this.usuarioDAO.registrarUsuario(usuarioNuevo);
    }

    public UsuarioDO obtenerUsuario(Long id) {
        return this.usuarioDAO.obtenerUsuario(id);
    }

    public void registrarUsuario(DatosRegistroDO datosRegistroDO) throws  Exception {
        // Verificar en Meta/Google
        LoginCredencialesDO credencialesDo = new LoginCredencialesDO();
        credencialesDo.setEmail(datosRegistroDO.getEmail());
        credencialesDo.setContrasenya(datosRegistroDO.getContrasenya());
        IAutenticacionGateway autenticacionGateway = AutenticacionGatewayFactory.crearAutenticationGateway(datosRegistroDO.getMetodoRegistro());
        autenticacionGateway.iniciarSesion(credencialesDo);

        // Verificar que un usuario con ese email no exista.
        if(usuarioDAO.obtenerUsuarioPorEmail(datosRegistroDO.getEmail().toLowerCase()) == null) {
            UsuarioDO nuevoUsuario = new UsuarioDO(datosRegistroDO);
            usuarioDAO.registrarUsuario(nuevoUsuario);
        } else {
            throw new Exception("Ya existe un usuario con ese correo");
        }
    }

    public TokenDO crearSesion(LoginCredencialesDO credencialesDo, MetodoRegistro metodoRegistro) throws Exception {
        // Verificar en Meta/Google
        IAutenticacionGateway autenticacionGateway = AutenticacionGatewayFactory.crearAutenticationGateway(metodoRegistro);
        autenticacionGateway.iniciarSesion(credencialesDo);

        // Una vez se verifica credenciales correctas, comprobarlas con las de Strava
        // Obtener usuario existente. Si falla, throwea.
        UsuarioDO usuarioExistente = usuarioDAO.obtenerUsuarioPorEmail(credencialesDo.getEmail().toLowerCase());

        if(usuarioExistente == null) {
            throw new Exception("El correo no esta registrado en Strava. Crea una cuenta.");
        }

        if(!metodoRegistro.equals(usuarioExistente.getMetodoRegistro())) {
            throw new Exception("El correo esta registrado con \"" + usuarioExistente.getMetodoRegistro().toString() + "\". Prueba a usar ese metodo al iniciar sesion.");
        }

        // Generar token y crear sesion
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int longitudToken = 10;
        char[] tokenAleatorio = new char[longitudToken];
        for(int i = 0; i < longitudToken; i++) {
            tokenAleatorio[i] = caracteres.charAt((int)(Math.random() * caracteres.length()));
        }
        String valorTokenAleatorio = new String(tokenAleatorio);

        TokenDO token = new TokenDO(valorTokenAleatorio);
        sesiones.put(token, usuarioExistente.getId());

        return token;
    }

    public void borrarSesion(TokenDO tokenDo) {
        sesiones.remove(tokenDo);
    }

    public boolean isTokenValido(TokenDO tokenDo) {
        return sesiones.containsKey(tokenDo);
    }

    public UsuarioDO conseguirUsuarioDeToken(TokenDO tokenDo) throws  Exception {
        if(sesiones.containsKey(tokenDo)) {
            return usuarioDAO.obtenerUsuario(sesiones.get(tokenDo));
        }
        throw new Exception("El token no es valido o no existe.");
    }
}
