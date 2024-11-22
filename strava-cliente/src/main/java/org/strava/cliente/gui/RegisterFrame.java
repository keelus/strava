package org.strava.cliente.gui;

import org.strava.cliente.Controlador;
import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;
import org.strava.cliente.gui.FormularioExterno.SeleccionFormularioExternoLogin;
import org.strava.server.Data.DTO.DatosRegistroDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Objects;

public class RegisterFrame extends JFrame {
    private JTextField emailField;
    private JTextField nombreField;
    private JTextField pesoKgField;
    private JTextField alturaCmField;
    private JTextField frecuenciaCardiacaMaxField;
    private JTextField frecuenciaCardiacaReposoField;
    private JComboBox<String> metodoRegistroComboBox;
    private JSpinner fechaNacimientoSpinner;

    private JPanel panelServicio;
    private JLabel loginIconLabel;

    public RegisterFrame() {
        setTitle("Registrarse");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(45, 52, 54));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        mainPanel.add(nombreLabel, gbc);

        nombreField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(nombreField, gbc);

        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaNacimientoLabel.setForeground(Color.WHITE);
        fechaNacimientoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(fechaNacimientoLabel, gbc);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        fechaNacimientoSpinner = new JSpinner(dateModel);
        fechaNacimientoSpinner.setFont(new Font("Roboto", Font.PLAIN, 14));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaNacimientoSpinner, "yyyy-MM-dd");
        fechaNacimientoSpinner.setEditor(dateEditor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(fechaNacimientoSpinner, gbc);

        JLabel pesoLabel = new JLabel("Peso (Kg):");
        pesoLabel.setForeground(Color.WHITE);
        pesoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(pesoLabel, gbc);

        pesoKgField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(pesoKgField, gbc);

        JLabel alturaLabel = new JLabel("Altura (cm):");
        alturaLabel.setForeground(Color.WHITE);
        alturaLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(alturaLabel, gbc);

        alturaCmField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(alturaCmField, gbc);

        JLabel fcMaxLabel = new JLabel("Frecuencia Cardiaca Maxima:");
        fcMaxLabel.setForeground(Color.WHITE);
        fcMaxLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(fcMaxLabel, gbc);

        frecuenciaCardiacaMaxField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(frecuenciaCardiacaMaxField, gbc);

        JLabel fcReposoLabel = new JLabel("Frecuencia Cardiaca en Reposo:");
        fcReposoLabel.setForeground(Color.WHITE);
        fcReposoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(fcReposoLabel, gbc);

        frecuenciaCardiacaReposoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(frecuenciaCardiacaReposoField, gbc);

        JLabel metodoRegistroLabel = new JLabel("Metodo de Registro:");
        metodoRegistroLabel.setForeground(Color.WHITE);
        metodoRegistroLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(metodoRegistroLabel, gbc);

        metodoRegistroComboBox = new JComboBox<>(new String[]{"Google", "Meta"});
        metodoRegistroComboBox.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(metodoRegistroComboBox, gbc);

        panelServicio = new JPanel(new BorderLayout());
        panelServicio .setBackground(new Color(255, 255, 255)); // fondo oscuro

        JPanel panelServicioDerecha = new JPanel(new GridBagLayout());
        panelServicioDerecha.setBackground(new Color(255, 255, 255)); // fondo oscuro
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelServicio.add(panelServicioDerecha, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        mainPanel.add(panelServicio, gbc);

        JButton registrarButton = new JButton("Finalizar registro");
        estilarButton(registrarButton);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        mainPanel.add(registrarButton, gbc);


        registrarButton.addActionListener(e -> registrarUsuario());

        JLabel loginLinkLabel = new JLabel("<html><u>¿Ya tienes una cuenta? Inicia sesion</u></html>");
        loginLinkLabel.setForeground(new Color(0, 184, 148));
        loginLinkLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        loginLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SeleccionFormularioExternoLogin();
                setVisible(false);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        mainPanel.add(loginLinkLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        loginIconLabel = new JLabel();
        panelServicio.add(loginIconLabel, BorderLayout.WEST);

        // CORREO
        JTextField correoField = new JTextField();
        correoField.setFont(new Font("Roboto", Font.PLAIN, 16));
        correoField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        //panelServicio.add(correoField, gbc);
        panelServicioDerecha.add(correoField, gbc);

        TextPrompt tpCorreo = new TextPrompt("Correo electrónico", correoField);
        tpCorreo.changeAlpha(128);
        tpCorreo.setHorizontalAlignment(JLabel.LEFT);

        // CONTRASEÑA
        JTextField contraField = new JPasswordField();
        contraField.setFont(new Font("Roboto", Font.PLAIN, 16));
        contraField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        panelServicioDerecha.add(contraField, gbc);

        TextPrompt tpContra = new TextPrompt("Contraseña", contraField);
        tpContra.changeAlpha(128);
        tpContra.setHorizontalAlignment(JLabel.LEFT);

        metodoRegistroComboBox.addActionListener(e -> {
            actualizarLogo();
        });
        actualizarLogo();


        add(mainPanel);
        setVisible(true);
    }

    private void actualizarLogo() {
        String nombreIcono = metodoRegistroComboBox.getSelectedItem().toString().toLowerCase().contains("google") ? "google" : "meta";
        ImageIcon imageIconTemporal = new ImageIcon(getClass().getResource("/" + nombreIcono + ".png"));

        int w = imageIconTemporal.getIconWidth();
        int h = imageIconTemporal.getIconHeight();
        float factorEscala = 1.0f/15.0f;

        ImageIcon googleIcon = new ImageIcon(imageIconTemporal.getImage().getScaledInstance((int)(w*factorEscala), (int)(h*factorEscala),  java.awt.Image.SCALE_SMOOTH));

        loginIconLabel.setIcon(googleIcon);
        loginIconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        DatosRegistroDTO datosRegistroDto = new DatosRegistroDTO();
        datosRegistroDto.setNombre(nombreField.getText());
        datosRegistroDto.setEmail(emailField.getText());
        datosRegistroDto.setFechaNacimiento((Date) fechaNacimientoSpinner.getValue());
        datosRegistroDto.setPesoKg(Double.parseDouble(pesoKgField.getText()));
        datosRegistroDto.setAlturaCm(Double.parseDouble(alturaCmField.getText()));
        datosRegistroDto.setFrecuenciaCardiacaMax(Integer.parseInt(frecuenciaCardiacaMaxField.getText()));
        datosRegistroDto.setFrecuenciaCardiacaReposo(Integer.parseInt(frecuenciaCardiacaReposoField.getText()));

        try {
            if (Objects.equals(metodoRegistroComboBox.getSelectedItem(), "Google")) {
                Controlador.getInstance().registrarUsuario(datosRegistroDto, FormularioExternoServicio.Google);
            } else {
                Controlador.getInstance().registrarUsuario(datosRegistroDto, FormularioExternoServicio.Meta);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getCause());
        }

        //try {
        //    System.out.println("Aqui ahora habria que mostrar la ventana de Login(ParaRegistro) de Meta o Google, con el email ya seteado y deshabilitado, para luego al final mandar todo al servidor.");
        //    return;

//            UsuarioNuevoDTO usuario = new UsuarioNuevoDTO();
//            usuario.setEmail(emailField.getText());
//            if(Objects.equals(metodoRegistroComboBox.getSelectedItem(), "Meta")) {
//                usuario.setMetodoRegistro(MetodoRegistro.Meta);
//            } else {
//                usuario.setMetodoRegistro(MetodoRegistro.Google);
//            }
//            usuario.setNombre(nombreField.getText());
//            usuario.setFechaNacimiento((Date) fechaNacimientoSpinner.getValue());
//            usuario.setPesoKg(Double.parseDouble(pesoKgField.getText()));
//            usuario.setAlturaCm(Double.parseDouble(alturaCmField.getText()));
//            usuario.setFrecuenciaCardiacaMax(Integer.parseInt(frecuenciaCardiacaMaxField.getText()));
//            usuario.setFrecuenciaCardiacaReposo(Integer.parseInt(frecuenciaCardiacaReposoField.getText()));
//
//            Controlador.getInstance().registrarUsuario(usuario);
//            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
//            new SeleccionFormularioExternoLogin();
//            dispose();
//        } catch (RemoteException | RuntimeException e) {
//            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getCause());
//        }
    }
}
