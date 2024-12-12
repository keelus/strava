package org.strava.server.Servicios;

import org.strava.server.AutenticacionGateway.AutenticacionGatewayFactory;
import org.strava.server.AutenticacionGateway.IAutenticacionGateway;
import org.strava.server.Data.Dominio.*;
import org.strava.server.Data.Enums.MetodoRegistro;

import java.util.*;

public class ServicioAutenticacion {
    private static ServicioAutenticacion instance;
    private final List<UsuarioDO> usuarios = new ArrayList<>();
    private final Map<TokenDO, UsuarioDO> sesiones = new HashMap<>();

    private ServicioAutenticacion() {
    }

    public static ServicioAutenticacion getInstance() {
        if(instance == null) {
            instance = new ServicioAutenticacion();
        }
        return instance;
    }

    public UsuarioDO conseguirUsuario(Long usuarioId) throws Exception {
        for(UsuarioDO usuarioDo : usuarios) {
            if(usuarioDo.getId().equals(usuarioId))
                return usuarioDo;
        }
        throw new Exception("No se han encontrado usuarios con esa ID.");
    }

    public void registrarUsuario(DatosRegistroDO datosRegistroDO) throws  Exception {
        // Verificar en Meta/Google
        LoginCredencialesDO credencialesDo = new LoginCredencialesDO();
        credencialesDo.setEmail(datosRegistroDO.getEmail());
        credencialesDo.setContrasenya(datosRegistroDO.getContrasenya());
        IAutenticacionGateway autenticacionGateway = AutenticacionGatewayFactory.crearAutenticationGateway(datosRegistroDO.getMetodoRegistro());
        autenticacionGateway.iniciarSesion(credencialesDo);

        // Una vez se verifican las credenciales del servicio externo, comprobar que no exista un usuario
        // con el correo.
        for(UsuarioDO usuarioDo: usuarios) {
            if(usuarioDo.getEmail().equalsIgnoreCase(datosRegistroDO.getEmail())) {
                throw new Exception("Ya existe un usuario con ese correo");
            }
        }

        // Si el email no esta en uso, se crea el usuario Do apartir de los datos de registro, y se guarda.
        UsuarioNuevoDO usuarioNuevoDo = new UsuarioNuevoDO();
        usuarioNuevoDo.setEmail(datosRegistroDO.getEmail());
        usuarioNuevoDo.setNombre(datosRegistroDO.getNombre());
        usuarioNuevoDo.setMetodoRegistro(datosRegistroDO.getMetodoRegistro());
        usuarioNuevoDo.setFechaNacimiento(datosRegistroDO.getFechaNacimiento());
        usuarioNuevoDo.setPesoKg(datosRegistroDO.getPesoKg());
        usuarioNuevoDo.setAlturaCm(datosRegistroDO.getAlturaCm());
        usuarioNuevoDo.setFrecuenciaCardiacaMax(datosRegistroDO.getFrecuenciaCardiacaMax());
        usuarioNuevoDo.setFrecuenciaCardiacaReposo(datosRegistroDO.getFrecuenciaCardiacaReposo());

        usuarios.add(new UsuarioDO(usuarioNuevoDo, Long.valueOf(usuarios.size())));
    }

    public TokenDO crearSesion(LoginCredencialesDO credencialesDo, MetodoRegistro metodoRegistro) throws Exception {
        // Verificar en Meta/Google
        IAutenticacionGateway autenticacionGateway = AutenticacionGatewayFactory.crearAutenticationGateway(metodoRegistro);
        autenticacionGateway.iniciarSesion(credencialesDo);

        // TODO

        // Una vez se verifica credenciales correctas, comprobarlas con las de Strava
        UsuarioDO usuarioExistente = null;
        for(UsuarioDO usuarioDo : usuarios) {
            if (usuarioDo.getEmail().equalsIgnoreCase(credencialesDo.getEmail())) {
                usuarioExistente = usuarioDo;
                break;
            }
        }

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
        sesiones.put(token, usuarioExistente);

        return token;
    }

    public void borrarSesion(TokenDO tokenDo) {
        if(sesiones.containsKey(tokenDo)) {
            sesiones.remove(tokenDo);
        }
    }

    public boolean isTokenValido(TokenDO tokenDo) {
        if(sesiones.containsKey(tokenDo)) {
            return true;
        }
        return false;
    }

    public UsuarioDO conseguirUsuarioDeToken(TokenDO tokenDo) throws  Exception {
        if(sesiones.containsKey(tokenDo)) {
            return sesiones.get(tokenDo);
        }
        throw new Exception("El token no es valido o no existe.");
    }
}
