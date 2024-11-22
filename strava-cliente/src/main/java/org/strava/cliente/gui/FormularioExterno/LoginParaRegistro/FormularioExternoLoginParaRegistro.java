package org.strava.cliente.gui.FormularioExterno.LoginParaRegistro;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;
import org.strava.cliente.gui.FormularioExterno.ResultadoFormularioExternoLogin;
import org.strava.cliente.gui.TextPrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioExternoLoginParaRegistro extends JFrame {
    DatosRegistro datosRegistro;
    CallbackFormularioExterno callback;

    JTextField correoField;
    JPasswordField contraField;

    JLabel googleIconLabel;
    ImageIcon googleIcon;

    public FormularioExternoLoginParaRegistro(
            CallbackFormularioExterno callback, FormularioExternoLoginParaRegistroAccionDeseada accionDeseada,
            DatosRegistro datosRegistro,
            String ubicacionLogo, String valorInicialCorreo, Color acento
    ) {
        this.callback = callback;
        this.datosRegistro = datosRegistro;

        setTitle("Iniciar sesion");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 255, 255)); // fondo oscuro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // LOGO GOOGLE
        ImageIcon tempGoogleImageIcon = new ImageIcon(getClass().getResource(ubicacionLogo));
        int w = tempGoogleImageIcon.getIconWidth();
        int h = tempGoogleImageIcon.getIconHeight();
        int s = 15;
        Image tempGoogleIcon = tempGoogleImageIcon.getImage();
        googleIcon = new ImageIcon(tempGoogleIcon.getScaledInstance(w/s, h/s,  Image.SCALE_SMOOTH));
        googleIconLabel= new JLabel(googleIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(googleIconLabel, gbc);

        // CORREO
        correoField = new JTextField(valorInicialCorreo);
        correoField.setEnabled(false);
        correoField.setFont(new Font("Roboto", Font.PLAIN, 16));
        correoField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(correoField, gbc);

        TextPrompt tpCorreo = new TextPrompt("Correo electrónico", correoField);
        tpCorreo.changeAlpha(128);
        tpCorreo.setHorizontalAlignment(JLabel.LEFT);

        // CONTRASEÑA
        contraField = new JPasswordField();
        contraField.setFont(new Font("Roboto", Font.PLAIN, 16));
        contraField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(contraField, gbc);

        TextPrompt tpContra = new TextPrompt("Contraseña", contraField);
        tpContra.changeAlpha(128);
        tpContra.setHorizontalAlignment(JLabel.LEFT);

        // BOTON LOGIN
        JButton loginButton = new JButton("Iniciar Sesion");
        loginButton.setFont(new Font("Roboto", Font.BOLD, 16));
        loginButton.setBackground(acento);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(12, 10, 8, 10));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String email = correoField.getText();
            char[] contrasenya = contraField.getPassword();

            // try {
            switch(accionDeseada) {
                case CON_GOOGLE:
                    // Controlador.registrarseConGoogle(email, contrasenya, datosRegistro) o algo similar. El servidor comprobara
                    // y si el email no esta registrado, y google es OK, registrara los datos una vez verificados
                    break;
                case CON_META:
                    // Controlador.iniciarConMeta(email, contrasenya, datosRegistro) o algo similar
                    break;
            }
            callback.onFormularioFinalizado(ResultadoFormularioExternoLogin.OK);
            dispose();
            // } catch (Exception e) {
            //     // Mostrar error aqui
            // }
        });

        // OPCION REGISTRARSE
        JLabel loginLinkLabel = new JLabel("<html><u>¿No tienes una cuenta? Registrate</u></html>");
        loginLinkLabel.setForeground(acento);
        loginLinkLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        loginLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("No hay que hacer registro de Google/Meta, asi que aqui podria haber un mensaje mostrando unas credenciales de ejemplo ya registradas.");
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
}
