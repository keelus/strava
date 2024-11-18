package org.strava.cliente.gui;

import org.strava.cliente.Controlador;
import org.strava.server.Data.DTO.UsuarioNuevoDTO;
import org.strava.server.Data.Enums.MetodoRegistro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Date;

public class RegisterFrame extends JFrame {

    private JTextField emailField;
    private JTextField nombreField;
    private JTextField pesoKgField;
    private JTextField alturaCmField;
    private JTextField frecuenciaCardiacaMaxField;
    private JTextField frecuenciaCardiacaReposoField;
    private JComboBox<String> metodoRegistroComboBox;
    private JSpinner fechaNacimientoSpinner;

    public RegisterFrame() {
        setTitle("Registrarse");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(45, 52, 54));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        mainPanel.add(emailField, gbc);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(nombreLabel, gbc);

        nombreField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(nombreField, gbc);

        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaNacimientoLabel.setForeground(Color.WHITE);
        fechaNacimientoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(fechaNacimientoLabel, gbc);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        fechaNacimientoSpinner = new JSpinner(dateModel);
        fechaNacimientoSpinner.setFont(new Font("Roboto", Font.PLAIN, 14));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaNacimientoSpinner, "yyyy-MM-dd");
        fechaNacimientoSpinner.setEditor(dateEditor);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(fechaNacimientoSpinner, gbc);

        JLabel pesoLabel = new JLabel("Peso (Kg):");
        pesoLabel.setForeground(Color.WHITE);
        pesoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(pesoLabel, gbc);

        pesoKgField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(pesoKgField, gbc);

        JLabel alturaLabel = new JLabel("Altura (cm):");
        alturaLabel.setForeground(Color.WHITE);
        alturaLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(alturaLabel, gbc);

        alturaCmField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(alturaCmField, gbc);

        JLabel fcMaxLabel = new JLabel("Frecuencia Cardiaca Msaxima:");
        fcMaxLabel.setForeground(Color.WHITE);
        fcMaxLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(fcMaxLabel, gbc);

        frecuenciaCardiacaMaxField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(frecuenciaCardiacaMaxField, gbc);

        JLabel fcReposoLabel = new JLabel("Frecuencia Cardiaca en Reposo:");
        fcReposoLabel.setForeground(Color.WHITE);
        fcReposoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(fcReposoLabel, gbc);

        frecuenciaCardiacaReposoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(frecuenciaCardiacaReposoField, gbc);

        JLabel metodoRegistroLabel = new JLabel("Metodo de Registro:");
        metodoRegistroLabel.setForeground(Color.WHITE);
        metodoRegistroLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(metodoRegistroLabel, gbc);

        metodoRegistroComboBox = new JComboBox<>(new String[]{"Escoger", "Meta", "Google"});
        metodoRegistroComboBox.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(metodoRegistroComboBox, gbc);

        JButton registrarButton = new JButton("Registrarse");
        estilarButton(registrarButton);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registrarButton, gbc);

        registrarButton.addActionListener(e -> registrarUsuario());

        JLabel loginLinkLabel = new JLabel("<html><u>¿Ya tienes una cuenta? Inicia sesion</u></html>");
        loginLinkLabel.setForeground(new Color(0, 184, 148));
        loginLinkLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        loginLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginFrame();
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginLinkLabel, gbc);

        add(mainPanel);
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

    private void registrarUsuario() {
        try {
            UsuarioNuevoDTO usuario = new UsuarioNuevoDTO();
            usuario.setEmail(emailField.getText());
            if(((String)metodoRegistroComboBox.getSelectedItem()).equals("Meta")) {
                usuario.setMetodoRegistro(MetodoRegistro.Meta);
            } else {
                usuario.setMetodoRegistro(MetodoRegistro.Google);
            }
            usuario.setNombre(nombreField.getText());
            usuario.setFechaNacimiento((Date) fechaNacimientoSpinner.getValue());
            usuario.setPesoKg(Double.parseDouble(pesoKgField.getText()));
            usuario.setAlturaCm(Double.parseDouble(alturaCmField.getText()));
            usuario.setFrecuenciaCardiacaMax(Integer.parseInt(frecuenciaCardiacaMaxField.getText()));
            usuario.setFrecuenciaCardiacaReposo(Integer.parseInt(frecuenciaCardiacaReposoField.getText()));

            Controlador.getInstance().registrarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
            new LoginFrame();
            dispose();
        } catch (RemoteException | RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getCause());
        }
    }
}
