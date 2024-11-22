package org.strava.cliente;

import javax.swing.*;

import org.strava.cliente.gui.LoginFrame;
import org.strava.cliente.gui.LoginGoogleFrame;

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
            SwingUtilities.invokeLater(LoginGoogleFrame::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
