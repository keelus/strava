package org.strava.cliente;

import org.strava.cliente.gui.AutenticacionFrame;
import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;
import org.strava.cliente.gui.FormularioExterno.SeleccionFormularioExternoLogin;
import org.strava.cliente.gui.ListarRetosAceptadosFrame;
import org.strava.cliente.gui.ListarRetosActivosFrame;
import org.strava.cliente.gui.ListarSesionesEntrenamientoFrame;

import javax.swing.*;

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

            SwingUtilities.invokeLater(AutenticacionFrame::new);
            // boolean debug = false;
            // if(debug) {
            //     // // Codigo temporal inicio
            //     char[] p = new char[]{'1', '2', '3', '4'};
            //     Controlador.getInstance().iniciarSesion("usuario1@gmail.com", p, FormularioExternoServicio.Google);

            //     new ListarRetosAceptadosFrame(Controlador.getInstance().tokenSesion);
            // } else {
            //}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
