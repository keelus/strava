package org.strava.server;

import org.strava.server.RemoteFachada.RemoteFachada;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        int puertoServicio = 4444;
        String nombreServicio = "StravaService";

        if(args.length != 2) {
            System.out.println("[WARNING] Usando puerto y nombre del servicio por defecto. Se recomienda ejecutar el programa con los dos argumentos:");
            System.out.println("\tjava [policy] [codebase] org.strava.server.Main [puertoServicio] [nombreServicio]");
        } else {
            puertoServicio = Integer.parseInt(args[0]);
            nombreServicio = args[1];
        }

        try {
            RemoteFachada remoteFachada = new RemoteFachada();

            // Creamos el RMI registry aqui, asi que no hace falta ejecutarlo aparte.
            Registry registry = LocateRegistry.createRegistry(puertoServicio);
            registry.rebind(nombreServicio, remoteFachada);

            System.out.println("Se ha iniciado el servidor (puerto " + puertoServicio+ ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}