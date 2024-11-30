package org.strava.cliente.gui;

import org.strava.cliente.Controlador;
import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;
import org.strava.cliente.gui.FormularioExterno.SeleccionFormularioExternoLogin;
import org.strava.server.Data.DTO.DatosRegistroDTO;
import org.strava.server.Data.Enums.MetodoRegistro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Objects;

public class RegisterFrame extends JFrame {
    private JTextField nombreField;
    private JTextField pesoKgField;
    private JTextField alturaCmField;
    private JTextField frecuenciaCardiacaMaxField;
    private JTextField frecuenciaCardiacaReposoField;
    private JComboBox<String> metodoRegistroComboBox;
    private JSpinner fechaNacimientoSpinner;

    private PanelServicioExterno panelServicio;

    public RegisterFrame() {
        setTitle("Registrarse");
        setSize(700, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(45, 52, 54));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel iconoLabel = Utils.crearLabelImagen(getClass().getResource("/strava.png"), 1.0f/10.0f);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        mainPanel.add(iconoLabel, gbc);

        JLabel categoriaLabel0 = new JLabel("Datos obligatorios");
        categoriaLabel0.setForeground(Color.WHITE);
        categoriaLabel0.setFont(new Font("Roboto", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(categoriaLabel0, gbc);

        gbc.gridwidth = 1;
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(nombreLabel, gbc);

        nombreField = new JTextField(20);
        nombreField.setFont(new Font("Roboto", Font.PLAIN, 14));
        nombreField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(nombreField, gbc);

        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaNacimientoLabel.setForeground(Color.WHITE);
        fechaNacimientoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(fechaNacimientoLabel, gbc);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        fechaNacimientoSpinner = new JSpinner(dateModel);
        fechaNacimientoSpinner .setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 150)));
        fechaNacimientoSpinner.setFont(new Font("Roboto", Font.PLAIN, 14));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaNacimientoSpinner, "yyyy-MM-dd");
        fechaNacimientoSpinner.setEditor(dateEditor);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(fechaNacimientoSpinner, gbc);

        JLabel categoriaLabel1 = new JLabel("Datos opcionales");
        categoriaLabel1.setForeground(Color.WHITE);
        categoriaLabel1.setFont(new Font("Roboto", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(categoriaLabel1, gbc);

        JLabel pesoLabel = new JLabel("Peso (Kg):");
        pesoLabel.setForeground(Color.WHITE);
        pesoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(pesoLabel, gbc);

        pesoKgField = new JTextField(20);
        pesoKgField.setFont(new Font("Roboto", Font.PLAIN, 14));
        pesoKgField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(pesoKgField, gbc);

        JLabel alturaLabel = new JLabel("Altura (cm):");
        alturaLabel.setForeground(Color.WHITE);
        alturaLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(alturaLabel, gbc);

        alturaCmField = new JTextField(20);
        alturaCmField.setFont(new Font("Roboto", Font.PLAIN, 14));
        alturaCmField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(alturaCmField, gbc);

        JLabel fcMaxLabel = new JLabel("Frecuencia Cardiaca Maxima:");
        fcMaxLabel.setForeground(Color.WHITE);
        fcMaxLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(fcMaxLabel, gbc);

        frecuenciaCardiacaMaxField = new JTextField(20);
        frecuenciaCardiacaMaxField.setFont(new Font("Roboto", Font.PLAIN, 14));
        frecuenciaCardiacaMaxField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(frecuenciaCardiacaMaxField, gbc);

        JLabel fcReposoLabel = new JLabel("Frecuencia Cardiaca en Reposo:");
        fcReposoLabel.setForeground(Color.WHITE);
        fcReposoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 8;
        mainPanel.add(fcReposoLabel, gbc);

        frecuenciaCardiacaReposoField = new JTextField(20);
        frecuenciaCardiacaReposoField.setFont(new Font("Roboto", Font.PLAIN, 14));
        frecuenciaCardiacaReposoField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(7, 3, 5, 10)));
        gbc.gridx = 1;
        gbc.gridy = 8;
        mainPanel.add(frecuenciaCardiacaReposoField, gbc);

        JLabel categoriaLabel2 = new JLabel("Metodo de registro");
        categoriaLabel2 .setForeground(Color.WHITE);
        categoriaLabel2.setFont(new Font("Roboto", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 9;
        mainPanel.add(categoriaLabel2, gbc);

        JLabel metodoRegistroLabel = new JLabel("Metodo de Registro:");
        metodoRegistroLabel.setForeground(Color.WHITE);
        metodoRegistroLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 10;
        mainPanel.add(metodoRegistroLabel, gbc);

        metodoRegistroComboBox = new JComboBox<>(new String[]{"Google", "Meta"});
        metodoRegistroComboBox.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 10;
        mainPanel.add(metodoRegistroComboBox, gbc);

        JButton registrarButton = new JButton("Finalizar registro");
        Utils.estilarButton(registrarButton);
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        mainPanel.add(registrarButton, gbc);

        registrarButton.addActionListener(e -> registrarUsuario());

        JLabel loginLinkLabel = new JLabel("<html><u>¿Ya tienes una cuenta? Inicia sesion</u></html>");
        loginLinkLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLinkLabel.setForeground(Utils.COLOR_ACENTO);
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
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        mainPanel.add(loginLinkLabel, gbc);


        panelServicio = new PanelServicioExterno();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        mainPanel.add(panelServicio, gbc);

        metodoRegistroComboBox.addActionListener(e -> {
            actualizarLogo();
        });
        actualizarLogo();


        add(mainPanel);
        setVisible(true);
    }

    private void actualizarLogo() {
        String nombreIcono = metodoRegistroComboBox.getSelectedItem().toString().toLowerCase().contains("google") ? "google" : "meta";
        panelServicio.setIcono(nombreIcono);
    }

    private void registrarUsuario() {
        DatosRegistroDTO datosRegistroDto = new DatosRegistroDTO();
        datosRegistroDto.setNombre(nombreField.getText());
        datosRegistroDto.setFechaNacimiento((Date) fechaNacimientoSpinner.getValue());
        datosRegistroDto.setPesoKg(Double.parseDouble(pesoKgField.getText()));
        datosRegistroDto.setAlturaCm(Double.parseDouble(alturaCmField.getText()));
        datosRegistroDto.setFrecuenciaCardiacaMax(Integer.parseInt(frecuenciaCardiacaMaxField.getText()));
        datosRegistroDto.setFrecuenciaCardiacaReposo(Integer.parseInt(frecuenciaCardiacaReposoField.getText()));

        datosRegistroDto.setEmail(panelServicio.getEmail());
        datosRegistroDto.setContrasenya(panelServicio.getContrasenya());

        System.out.println(datosRegistroDto.getEmail());
        System.out.println(datosRegistroDto.getContrasenya());

        try {
            if (Objects.equals(metodoRegistroComboBox.getSelectedItem(), "Google")) {
                datosRegistroDto.setMetodoRegistro(MetodoRegistro.Google);
                Controlador.getInstance().registrarUsuario(datosRegistroDto, FormularioExternoServicio.Google);
            } else {
                datosRegistroDto.setMetodoRegistro(MetodoRegistro.Meta);
                Controlador.getInstance().registrarUsuario(datosRegistroDto, FormularioExternoServicio.Meta);
            }

            // Si llegamos aqui, significa que se ha registrado con exito
            JOptionPane.showMessageDialog(null, "Cuenta registrada correctamente! Ya puedes iniciar sesion.");
            new SeleccionFormularioExternoLogin();
            dispose();
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getCause());
        }
    }
}
