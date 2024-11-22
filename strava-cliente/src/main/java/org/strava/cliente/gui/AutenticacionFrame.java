package org.strava.cliente.gui;

import org.strava.cliente.gui.FormularioExterno.*;

import javax.swing.*;
import java.awt.*;

public class AutenticacionFrame extends JFrame {
    public AutenticacionFrame() {
        setTitle("Menú Principal");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(new Color(45, 52, 54));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel iconoLabel = Utils.crearLabelImagen(getClass().getResource("/strava.png"), 1.0f/15.0f);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(iconoLabel, gbc);

        // boton crear reto
        JButton loginButton = new JButton("Login");
        Utils.estilarButton(loginButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        menuPanel.add(loginButton, gbc);

        // boton listar retos
        JButton registroButton = new JButton("Registro");
        Utils.estilarButton(registroButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        menuPanel.add(registroButton, gbc);

        loginButton.addActionListener(e -> {
            SeleccionFormularioExternoLogin seleccionFormulario = new SeleccionFormularioExternoLogin();
            seleccionFormulario.setVisible(true);
            dispose();
        });
        registroButton.addActionListener(e -> {
            new RegisterFrame().setVisible(true);
            dispose();
        });


        add(menuPanel);
        setVisible(true);
    }
}
