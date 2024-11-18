package org.strava.server;

import org.strava.server.Data.DTO.UsuarioNuevoDTO;
import org.strava.server.Data.Enums.MetodoRegistro;
import org.strava.server.RemoteFachada.RemoteFachada;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        try {
            RemoteFachada remoteFachada = new RemoteFachada();

            // Registrar usuario de prueba
            // TODO: Quitar esto
            UsuarioNuevoDTO usuarioDto = new UsuarioNuevoDTO();
            usuarioDto.setEmail("admin");
            usuarioDto.setNombre("admin");
            usuarioDto.setMetodoRegistro(MetodoRegistro.Google);
            usuarioDto.setFrecuenciaCardiacaMax(100);
            usuarioDto.setFrecuenciaCardiacaReposo(100);
            usuarioDto.setAlturaCm(100.0);
            usuarioDto.setFechaNacimiento(new Date());

            remoteFachada.authRegistrar(usuarioDto);

            Registry registry = LocateRegistry.createRegistry(4444);
            registry.rebind("remoteFachada", remoteFachada);

            System.out.println("Se ha iniciado el servidor (RMI's Version)");
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
