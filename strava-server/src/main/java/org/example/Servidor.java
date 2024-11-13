package org.example;

import org.example.implementaciones.ServicioRetoImpl;
import org.example.implementaciones.ServicioUsuarioImpl;
import org.example.interfaces.ServicioReto;
import org.example.interfaces.ServicioUsuario;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) {
        try {
            ServicioUsuario usuarioService = new ServicioUsuarioImpl();
            ServicioReto retoService = new ServicioRetoImpl();

            Registry registry = LocateRegistry.createRegistry(4444);
            registry.rebind("usuarioService", usuarioService);
            registry.rebind("retoService", retoService);

            System.out.println("Se ha iniciado el servidor (RMI's Version)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
