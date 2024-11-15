package org.example;

import org.example.facade.implementaciones.AuthController;
import org.example.facade.implementaciones.RetoController;
import org.example.service.RetoService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) {
        try {
            AuthController authController = new AuthController();
            RetoController retoController = new RetoController();

            Registry registry = LocateRegistry.createRegistry(4444);
            registry.rebind("authController", authController);
            registry.rebind("retoController", retoController);

            System.out.println("Se ha iniciado el servidor (RMI's Version)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
