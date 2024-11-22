package org.strava.cliente.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class PanelServicioExterno extends JPanel {
    private JLabel iconoLabel;
    private JTextField emailField;
    private JPasswordField contrasenyaField;

    public PanelServicioExterno() {
        super(new BorderLayout());
        setBackground(new Color(255, 255, 255));

        // Parte izquierda
        iconoLabel = Utils.crearLabelImagen(getClass().getResource("/google.png"), 1.0f/15.0f);
        add(iconoLabel, BorderLayout.WEST);

        // Parte derecha
        JPanel panelFields = new JPanel(new GridBagLayout());
        panelFields .setBackground(new Color(255, 255, 255));
        add(panelFields, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 1;


        // EMAIL
        emailField = new JTextField();
        emailField.setFont(new Font("Roboto", Font.PLAIN, 16));
        emailField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridy = 0;

        TextPrompt tpEmail = new TextPrompt("Dirección de email", emailField);
        tpEmail.changeAlpha(128);
        tpEmail.setHorizontalAlignment(JLabel.LEFT);

        panelFields.add(emailField, gbc);

        // CONTRASEÑA
        contrasenyaField = new JPasswordField();
        contrasenyaField.setFont(new Font("Roboto", Font.PLAIN, 16));
        contrasenyaField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridy = 1;

        TextPrompt tpContrasenya = new TextPrompt("Contraseña", contrasenyaField);
        tpContrasenya.changeAlpha(128);
        tpContrasenya.setHorizontalAlignment(JLabel.LEFT);

        panelFields.add(contrasenyaField, gbc);
    }

    public void setIcono(String nombreServicio) {
        iconoLabel.setIcon(Utils.crearLabelImagen(getClass().getResource("/" + nombreServicio + ".png"), 1.0f/15.0f).getIcon());
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getContrasenya() {
        return new String(contrasenyaField.getPassword());
    }
}
