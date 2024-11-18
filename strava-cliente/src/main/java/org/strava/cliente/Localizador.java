package org.strava.cliente;

import org.strava.server.RemoteFachada.IRemoteFachada;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Localizador {
    private static IRemoteFachada remoteFachada;

    public static IRemoteFachada getRemoteFachada() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);
            remoteFachada = (IRemoteFachada) registry.lookup("remoteFachada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse al servidor: " + e.getCause());
            System.out.println(e);
        }

        return remoteFachada;
    }
}
