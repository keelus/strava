package org.strava.cliente.gui;

import org.strava.cliente.Controlador;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class LoginGoogleFrame extends JFrame {
    JTextField correoField;
    JPasswordField contraField;

    JLabel testLabel;
    ImageIcon googleIcon;

    public LoginGoogleFrame() {
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


        ImageIcon tempGoogleImageIcon = new ImageIcon(getClass().getResource("/google.png"));
        int w = tempGoogleImageIcon.getIconWidth();
        int h = tempGoogleImageIcon.getIconHeight();
        int s = 15;
        Image tempGoogleIcon = tempGoogleImageIcon.getImage();
        googleIcon = new ImageIcon(tempGoogleIcon.getScaledInstance(w/s, h/s,  java.awt.Image.SCALE_SMOOTH));
        testLabel = new JLabel(googleIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(testLabel, gbc);

        // titulo
        /*JLabel tituloLabel = new JLabel("Iniciar Sesion", SwingConstants.CENTER);
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(tituloLabel, gbc);*/

        // textfield usuario
        correoField = new JTextField();
        correoField.setFont(new Font("Roboto", Font.PLAIN, 16));
        correoField.setBorder(BorderFactory.createEmptyBorder(7, 3, 5, 10));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(correoField, gbc);

        TextPrompt tpCorreo = new TextPrompt("Correo electrónico", correoField);
        tpCorreo.changeAlpha(128);
        tpCorreo.setHorizontalAlignment(JLabel.LEFT);

        // textfield contraseña
        contraField = new JPasswordField();
        contraField.setFont(new Font("Roboto", Font.PLAIN, 16));
        contraField.setBorder(BorderFactory.createEmptyBorder(7, 3, 5, 10));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(contraField, gbc);

        TextPrompt tpContra = new TextPrompt("Contraseña", contraField);
        tpContra.changeAlpha(128);
        tpContra.setHorizontalAlignment(JLabel.LEFT);

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

        loginButton.addActionListener(e -> {
            try {
                Controlador.getInstance().iniciarSesion(correoField.getText(), contraField.getPassword());
                dispose();
                new MainFrame();
            } catch (RemoteException err) {
                JOptionPane.showMessageDialog(this, "Error al iniciar sesión: " + err.getCause());
            }
        });

        JLabel loginLinkLabel = new JLabel("<html><u>¿No tienes una cuenta? Registrate</u></html>");
        loginLinkLabel.setForeground(new Color(0, 184, 148));
        loginLinkLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        loginLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RegisterFrame();
                dispose();
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginLinkLabel, gbc);

        // Añadir el panel principal al frame
        add(mainPanel);
        setVisible(true);
    }

    private void iniciarSesion() {
    }
}
