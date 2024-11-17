package org.strava.cliente.gui;

import org.strava.server.Data.DTO.TokenDTO;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TokenDTO tokenSesion;

    public MainFrame(TokenDTO tokenSesion) {
        this.tokenSesion = tokenSesion;

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
        JButton crearRetoButton = new JButton("Crear Reto");
        estilarButton(crearRetoButton);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(crearRetoButton, gbc);

        // boton listar retos
        JButton listarButton = new JButton("Listar Retos");
        estilarButton(listarButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        menuPanel.add(listarButton, gbc);

        // boton cerrar sesión
        JButton cerrarButton = new JButton("Cerrar Sesión");
        estilarButton(cerrarButton);
        cerrarButton.setBackground(new Color(231, 76, 60));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        menuPanel.add(cerrarButton, gbc);

        crearRetoButton.addActionListener(e -> {
            new CrearRetoFrame(this.tokenSesion);
        });
        listarButton.addActionListener(e -> {
            new ListarRetosFrame(this.tokenSesion);
        });
        cerrarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(MainFrame.this, "Sesión cerrada");
            new LoginFrame().setVisible(true);
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
