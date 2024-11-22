package org.strava.cliente.gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Utils {
    public static final Color COLOR_ACENTO = new Color(252, 75, 0);
    public static void estilarButton(JButton button) {
        button.setFont(new Font("Roboto", Font.BOLD, 16));
        button.setBackground(COLOR_ACENTO);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 10, 8, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static JLabel crearLabelImagen(URL ubicacionImagen, float factorEscala) {
        ImageIcon imageIconTemporal = new ImageIcon(ubicacionImagen);

        int w = imageIconTemporal.getIconWidth();
        int h = imageIconTemporal.getIconHeight();

        ImageIcon icono = new ImageIcon(imageIconTemporal.getImage().getScaledInstance((int)(w*factorEscala), (int)(h*factorEscala),  java.awt.Image.SCALE_SMOOTH));

        JLabel label = new JLabel(icono);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }
}
