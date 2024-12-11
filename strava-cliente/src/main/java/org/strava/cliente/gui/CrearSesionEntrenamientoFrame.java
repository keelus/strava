package org.strava.cliente.gui;

import org.strava.cliente.Controlador;
import org.strava.server.Data.DTO.SesionEntrenamientoNuevoDTO;
import org.strava.server.Data.DTO.TokenDTO;
import org.strava.server.Data.Enums.Deporte;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.Date;

public class CrearSesionEntrenamientoFrame extends JFrame {

    private TokenDTO tokenSesion;
    private JTextField tituloField;
    private JSpinner fechaInicioSpinner;
    private JSpinner horaInicioSpinner;
    private JTextField duracionField;
    private JComboBox<String> deporteComboBox;
    private JTextField distanciaField; // Km

    public CrearSesionEntrenamientoFrame(TokenDTO tokenSesion) {
        this.tokenSesion = tokenSesion;

        setTitle("Crear sesion de entrenamiento");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(45, 52, 54));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // label nombre reto
        JLabel tituloLabel = new JLabel("Titulo de la sesion:");
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(tituloLabel, gbc);

        // textfield nombre reto
        tituloField = new JTextField(20);
        tituloField.setFont(new Font("Roboto", Font.PLAIN, 14));
        tituloField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        mainPanel.add(tituloField, gbc);

        // Fecha inicio label
        JLabel fechaInicioLabel = new JLabel("Fecha de inicio:");
        fechaInicioLabel.setForeground(Color.WHITE);
        fechaInicioLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(fechaInicioLabel, gbc);

        // Fecha inicio spinner
        SpinnerDateModel dateModel = new SpinnerDateModel();
        fechaInicioSpinner = new JSpinner(dateModel);
        fechaInicioSpinner.setFont(new Font("Roboto", Font.PLAIN, 14));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaInicioSpinner, "yyyy-MM-dd");
        fechaInicioSpinner.setEditor(dateEditor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(fechaInicioSpinner, gbc);

        // Hora inicio label
        JLabel horaInicioLabel = new JLabel("Hora de inicio:");
        horaInicioLabel.setForeground(Color.WHITE);
        horaInicioLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(horaInicioLabel, gbc);

        // Hora fin spinner
        SpinnerDateModel dateModel1 = new SpinnerDateModel();
        horaInicioSpinner = new JSpinner(dateModel1);
        horaInicioSpinner.setFont(new Font("Roboto", Font.PLAIN, 14));
        JSpinner.DateEditor dateEditor1 = new JSpinner.DateEditor(horaInicioSpinner, "hh:mm:ss");
        horaInicioSpinner.setEditor(dateEditor1);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(horaInicioSpinner, gbc);


        // Deporte label
        JLabel deporteLabel = new JLabel("Deporte:");
        deporteLabel.setForeground(Color.WHITE);
        deporteLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(deporteLabel, gbc);

        // Tipo objetivo combo box
        deporteComboBox = new JComboBox<>(new String[]{"Running", "Ciclismo"});
        deporteComboBox.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(deporteComboBox, gbc);

        // Duracion label
        JLabel duracionLabel = new JLabel("Duracion (min): ");
        duracionLabel.setForeground(Color.WHITE);
        duracionLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(duracionLabel, gbc);


        // Duracion field
        duracionField = new JTextField(20);
        duracionField.setFont(new Font("Roboto", Font.PLAIN, 14));
        duracionField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1;
        mainPanel.add(duracionField, gbc);

        // Distancia label
        JLabel distanciaLabel = new JLabel("Distancia (km): ");
        distanciaLabel.setForeground(Color.WHITE);
        distanciaLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(distanciaLabel, gbc);


        // Distancia field
        distanciaField = new JTextField(20);
        distanciaField.setFont(new Font("Roboto", Font.PLAIN, 14));
        distanciaField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1;
        mainPanel.add(distanciaField, gbc);

        // boton crear label
        JButton crearButton = new JButton("Crear");
        Utils.estilarButton(crearButton);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(crearButton, gbc);

        crearButton.addActionListener(e -> crearSesion());

        add(mainPanel);
        setVisible(true);
    }

    private void crearSesion() {
        try {
            if(tituloField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "¡El titulo de la sesion de entrenamiento no puede estar vacio!");
                return;
            }

            if(distanciaField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "¡La distancia de la sesion no puede estar vacia!");
                return;
            }

            SesionEntrenamientoNuevoDTO sesion = new SesionEntrenamientoNuevoDTO();
            sesion.setTitulo(tituloField.getText());

            Date fechaInicio = (Date)fechaInicioSpinner.getValue();
            // Hora, min y sec a 0
            Date fechaInicioCorrecta = new Date(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDate(), 0, 0, 0);
            sesion.setFechaInicio(fechaInicioCorrecta);

            // Esto queda por hacer
            // Date horaInicio = (Date)horaInicioSpinner.getValue();
            sesion.setHoraInicio(0);

            Duration duracion = Duration.ofMinutes(Integer.valueOf(duracionField.getText()));
            sesion.setDuracion(duracion);

            if(deporteComboBox.getSelectedItem().toString().equals("Ciclismo")) {
                sesion.setDeporte(Deporte.Ciclismo);
            } else {
                sesion.setDeporte(Deporte.Running);
            }

            Controlador.getInstance().crearSesionEntrenamiento(sesion);
            JOptionPane.showMessageDialog(this, "Sesion de entrenamiento creada exitosamente.");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al crear el reto: " + e.getCause());
        }
    }
}
