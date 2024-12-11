package org.strava.cliente.gui;

import org.strava.server.Data.DTO.TokenDTO;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TokenDTO tokenSesion;

    public MainFrame() {
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
        JButton crearRetoButton = new JButton("Crear reto");
        Utils.estilarButton(crearRetoButton);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(crearRetoButton, gbc);

        // boton crear sesion
        JButton crearSesionButton = new JButton("Crear sesion de entrenamiento");
        Utils.estilarButton(crearSesionButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        menuPanel.add(crearSesionButton, gbc);

        // boton listar retos
        JButton listarRetosActivosButton = new JButton("Listar retos activos");
        Utils.estilarButton(listarRetosActivosButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        menuPanel.add(listarRetosActivosButton, gbc);

        // boton listar retos
        JButton listarRetosAceptadosButton = new JButton("Listar retos aceptados");
        Utils.estilarButton(listarRetosAceptadosButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        menuPanel.add(listarRetosAceptadosButton, gbc);

        // boton listar sesiones
        JButton listarSesionesEntrenamientoButton = new JButton("Listar sesiones de entrenamiento");
        Utils.estilarButton(listarSesionesEntrenamientoButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        menuPanel.add(listarSesionesEntrenamientoButton, gbc);

        // boton cerrar sesión
        JButton cerrarButton = new JButton("Cerrar sesión");
        Utils.estilarButton(cerrarButton);
        cerrarButton.setBackground(new Color(231, 76, 60));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        menuPanel.add(cerrarButton, gbc);

        crearRetoButton.addActionListener(e -> {
            new CrearRetoFrame(this.tokenSesion);
        });
        crearSesionButton.addActionListener(e -> {
            new CrearSesionEntrenamientoFrame(this.tokenSesion);
        });
        listarRetosActivosButton.addActionListener(e -> {
            new ListarRetosActivosFrame(this.tokenSesion);
            dispose();
        });
        listarRetosAceptadosButton.addActionListener(e -> {
            new ListarRetosAceptadosFrame(this.tokenSesion);
            dispose();
        });
        listarSesionesEntrenamientoButton.addActionListener(e -> {
            new ListarSesionesEntrenamientoFrame(this.tokenSesion);
            dispose();
        });
        cerrarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(MainFrame.this, "Sesión cerrada");
            new AutenticacionFrame().setVisible(true);
            dispose();
        });

        add(menuPanel);
        setVisible(true);
    }
}
