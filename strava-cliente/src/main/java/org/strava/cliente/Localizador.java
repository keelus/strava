package org.strava.cliente;

import org.strava.server.RemoteFachada.IRemoteFachada;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Localizador {
    private static int puertoServicio;
    private static String nombreServicio;

    private static IRemoteFachada remoteFachada;

    public static void bindearServer(int puerto, String nombre) {
        puertoServicio = puerto;
        nombreServicio = nombre;
    }

    public static IRemoteFachada getRemoteFachada() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", puertoServicio);
            remoteFachada = (IRemoteFachada) registry.lookup(nombreServicio);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse al servidor: " + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la fachada remota del servidor: " + e);
        }

        return remoteFachada;
    }
}
