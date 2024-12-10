package org.strava.cliente;

import javax.swing.*;

import org.strava.cliente.gui.AutenticacionFrame;
import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;
import org.strava.cliente.gui.ListarRetosFrame;
import org.strava.server.Data.DTO.LoginCredencialesDTO;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) {
        int puertoServicio = 4444;
        String nombreServicio = "StravaService";


        if(args.length != 2) {
            System.out.println("[WARNING] Usando puerto y nombre del servicio por defecto. Se recomienda ejecutar el programa con los dos argumentos:");
            System.out.println("\tjava [policy] [codebase] org.strava.cliente.Main [puertoServicio] [nombreServicio]");
        } else {
            puertoServicio = Integer.parseInt(args[0]);
            nombreServicio = args[1];
        }

        Localizador.bindearServer(puertoServicio, nombreServicio);


        try {
            // Activa el antialiasing para el texto
            System.setProperty("awt.useSystemAAFontSettings", "on");

            // SwingUtilities.invokeLater(AutenticacionFrame::new);

            // Codigo temporal inicio
            char[] p = new char[]{'p', 'a', 's', 's'};
            Controlador.getInstance().iniciarSesion("user@gmail.com", p, FormularioExternoServicio.Google);

            new ListarRetosFrame(Controlador.getInstance().tokenSesion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
