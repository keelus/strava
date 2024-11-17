package org.strava.server;

import org.strava.server.RemoteFachada.RemoteFachada;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) {
        try {
            RemoteFachada remoteFachada = new RemoteFachada();
            // AuthController authController = new AuthController();
            // RetoController retoController = new RetoController();

            // UsuarioDTO usuarioDto = new UsuarioDTO();
            // usuarioDto.setEmail("admin");
            // usuarioDto.setNombre("admin");
            // usuarioDto.setMetodoRegistro("Google");
            // usuarioDto.setFrecuenciaCardiacaMax(100);
            // usuarioDto.setFrecuenciaCardiacaReposo(100);
            // usuarioDto.setAlturaCm(100.0);
            // usuarioDto.setFechaNacimiento(new Date());
            //authController.registrar(usuarioDto);

            Registry registry = LocateRegistry.createRegistry(4444);
            //registry.rebind("authController", authController);
            //registry.rebind("retoController", retoController);
            registry.rebind("remoteFachada", remoteFachada);

            System.out.println("Se ha iniciado el servidor (RMI's Version)");
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
