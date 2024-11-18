package org.strava.cliente;

import javax.swing.*;

import org.strava.cliente.gui.LoginFrame;
import org.strava.server.RemoteFachada.IRemoteFachada;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static IRemoteFachada remoteFachada;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);
            remoteFachada = (IRemoteFachada) registry.lookup("remoteFachada");

            // Iniciar ventana login
            SwingUtilities.invokeLater(LoginFrame::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
