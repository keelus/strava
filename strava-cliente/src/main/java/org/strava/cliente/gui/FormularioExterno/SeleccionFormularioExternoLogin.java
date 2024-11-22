package org.strava.cliente.gui.FormularioExterno;

import javax.swing.*;
import java.awt.*;

import org.strava.cliente.gui.AutenticacionFrame;
import org.strava.cliente.gui.FormularioExterno.Login.FormularioExternoLogin;
import org.strava.cliente.gui.FormularioExterno.Login.FormularioExternoLoginGoogle;
import org.strava.cliente.gui.FormularioExterno.Login.FormularioExternoLoginMeta;
import org.strava.cliente.gui.MainFrame;


public class SeleccionFormularioExternoLogin extends JFrame implements CallbackFormularioExterno {
    public SeleccionFormularioExternoLogin() {
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
        JButton googleButton = new JButton("Google");
        estilarButton(googleButton);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(googleButton, gbc);

        // boton listar retos
        JButton metaButton = new JButton("Meta");
        estilarButton(metaButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        menuPanel.add(metaButton, gbc);

        JButton volverButton = new JButton("-- Volver --");
        estilarButton(volverButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        menuPanel.add(volverButton, gbc);

        googleButton.addActionListener(e -> {
            FormularioExternoLogin form = new FormularioExternoLoginGoogle(this);
            form.setVisible(true);
            dispose();
        });
        metaButton.addActionListener(e -> {
            FormularioExternoLogin form = new FormularioExternoLoginMeta(this);
            form.setVisible(true);
            dispose();
        });

        volverButton.addActionListener(e -> {
            AutenticacionFrame autenticacionFrame = new AutenticacionFrame();
            autenticacionFrame.setVisible(true);
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

    @Override
    public void onFormularioFinalizado(ResultadoFormularioExternoLogin resultado) {
        switch(resultado) {
            case OK:
                // El token DTO sera introducido en el Controlador, por las dos funciones correspondientes
                dispose();
                new MainFrame();
                break;
            case VOLVER:
                dispose();
                new SeleccionFormularioExternoLogin();
                break;
        }
    }
}
