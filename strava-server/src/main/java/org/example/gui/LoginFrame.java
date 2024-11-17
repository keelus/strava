package org.example.gui;

import org.example.Cliente;
import org.example.entity.dto.LoginCredencialesDTO;
import org.example.entity.dto.TokenDTO;
import org.example.entity.dto.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class LoginFrame extends JFrame {
    JTextField usuarioField;
    JPasswordField contraField;

    public LoginFrame() {
        setTitle("Iniciar sesion");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(45, 52, 54)); // fondo oscuro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // titulo
        JLabel tituloLabel = new JLabel("Iniciar Sesion", SwingConstants.CENTER);
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(tituloLabel, gbc);

        // label usuario
        JLabel usuarioLabel = new JLabel("Usuario");
        usuarioLabel.setForeground(Color.WHITE);
        usuarioLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(usuarioLabel, gbc);

        // textfield usuario
        usuarioField = new JTextField();
        usuarioField.setFont(new Font("Roboto", Font.PLAIN, 16));
        usuarioField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(usuarioField, gbc);

        // label contraseña
        JLabel contraLabel = new JLabel("Contraseña");
        contraLabel.setForeground(Color.WHITE);
        contraLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(contraLabel, gbc);

        // textfield contraseña
        contraField = new JPasswordField();
        contraField.setFont(new Font("Roboto", Font.PLAIN, 16));
        contraField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(contraField, gbc);

        // boton login
        JButton loginButton = new JButton("Iniciar Sesion");
        loginButton.setFont(new Font("Roboto", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 184, 148));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton, gbc);

        // boton registrar
        JButton registrarseButton = new JButton("Registrarse");
        registrarseButton.setFont(new Font("Roboto", Font.BOLD, 16));
        registrarseButton.setBackground(new Color(209, 96, 36));
        registrarseButton.setForeground(Color.WHITE);
        registrarseButton.setFocusPainted(false);
        registrarseButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        registrarseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(registrarseButton, gbc);

        loginButton.addActionListener(e -> iniciarSesion());
        registrarseButton.addActionListener(e -> registrarUsuario());

        // Añadir el panel principal al frame
        add(mainPanel);
    }

    private void iniciarSesion() {
        try {
            LoginCredencialesDTO credenciales = new LoginCredencialesDTO();
            credenciales.setEmail(usuarioField.getText());
            credenciales.setContrasenya(new String(contraField.getPassword()));

            TokenDTO token = Cliente.authController.login(credenciales);
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. Token: " + token.getValor());
            dispose();
            new MainFrame();
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión: " + e.getCause());
        }
    }

    private void registrarUsuario() {
        try {
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setEmail(usuarioField.getText());

            Cliente.authController.registrar(usuario);
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getCause());
        }
    }
}
