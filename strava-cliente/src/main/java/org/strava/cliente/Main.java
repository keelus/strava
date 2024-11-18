package org.strava.cliente;

import javax.swing.*;

import org.strava.cliente.gui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        try {
            // Iniciar ventana login
            SwingUtilities.invokeLater(LoginFrame::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
