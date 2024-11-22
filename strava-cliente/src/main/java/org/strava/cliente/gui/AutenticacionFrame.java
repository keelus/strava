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

        // boton crear reto
        JButton loginButton = new JButton("Login");
        estilarButton(loginButton);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(loginButton, gbc);

        // boton listar retos
        JButton registroButton = new JButton("Registro");
        estilarButton(registroButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
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

    // hacer botones agradables a la vista
    private void estilarButton(JButton button) {
        button.setFont(new Font("Roboto", Font.BOLD, 16));
        button.setBackground(new Color(0, 184, 148));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
