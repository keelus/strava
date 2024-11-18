package org.strava.cliente.gui;

import org.strava.cliente.Controlador;
import org.strava.server.Data.DTO.RetoNuevoDTO;
import org.strava.server.Data.DTO.TokenDTO;
import org.strava.server.Data.Enums.Deporte;
import org.strava.server.Data.Enums.TipoObjetivo;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class CrearRetoFrame extends JFrame {
    private TokenDTO tokenSesion;
    private JTextField nombreRetoField;
    private JSpinner fechaInicioSpinner;
    private JSpinner fechaFinSpinner;
    private JComboBox<String> tipoObjetivoComboBox;
    private JTextField valorObjetivoField;

    private JCheckBox deporteRunningCheckbox;
    private JCheckBox deporteCiclismoCheckbox;

    public CrearRetoFrame(TokenDTO tokenSesion) {
        this.tokenSesion = tokenSesion;

        setTitle("Crear Reto");
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
        JLabel nombreLabel = new JLabel("Nombre del Reto:");
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(nombreLabel, gbc);

        // textfield nombre reto
        nombreRetoField = new JTextField(20);
        nombreRetoField.setFont(new Font("Roboto", Font.PLAIN, 14));
        nombreRetoField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        mainPanel.add(nombreRetoField, gbc);

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

        // Fecha fin label
        JLabel fechaFinLabel = new JLabel("Fecha fin:");
        fechaFinLabel.setForeground(Color.WHITE);
        fechaFinLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(fechaFinLabel, gbc);

        // Fecha fin spinner
        SpinnerDateModel dateModel1 = new SpinnerDateModel();
        fechaFinSpinner= new JSpinner(dateModel1);
        fechaFinSpinner.setFont(new Font("Roboto", Font.PLAIN, 14));
        JSpinner.DateEditor dateEditor1 = new JSpinner.DateEditor(fechaFinSpinner, "yyyy-MM-dd");
        fechaFinSpinner.setEditor(dateEditor1);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(fechaFinSpinner, gbc);

        // Tipo objetivo label
        JLabel tipoObjetivoLabel = new JLabel("Tipo de objetivo: ");
        tipoObjetivoLabel.setForeground(Color.WHITE);
        tipoObjetivoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(tipoObjetivoLabel, gbc);

        // Tipo objetivo combo box
        tipoObjetivoComboBox= new JComboBox<>(new String[]{"Distancia", "Tiempo"});
        tipoObjetivoComboBox.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(tipoObjetivoComboBox, gbc);

        // Valor objetivo label label
        JLabel valorObjetivoLabel = new JLabel("Valor objetivo (km): ");
        valorObjetivoLabel.setForeground(Color.WHITE);
        valorObjetivoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(valorObjetivoLabel, gbc);


        // Valor objetivo textfield
        valorObjetivoField= new JTextField(20);
        valorObjetivoField.setFont(new Font("Roboto", Font.PLAIN, 14));
        valorObjetivoField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1;
        mainPanel.add(valorObjetivoField, gbc);

        // Deporte running reto
        JLabel deporteRunningLabel = new JLabel("Running: ");
        deporteRunningLabel.setForeground(Color.WHITE);
        deporteRunningLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(deporteRunningLabel, gbc);

        // Deporte running checkbox
        deporteRunningCheckbox= new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0;
        mainPanel.add(deporteRunningCheckbox, gbc);

        // Deporte ciclismo label
        JLabel deporteCiclismoLabel = new JLabel("Ciclismo: ");
        deporteCiclismoLabel.setForeground(Color.WHITE);
        deporteCiclismoLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(deporteCiclismoLabel, gbc);

        // Deporte ciclismo checkbox
        deporteCiclismoCheckbox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 0;
        mainPanel.add(deporteCiclismoCheckbox, gbc);

        // boton crear label
        JButton crearButton = new JButton("Crear");
        estilarButton(crearButton);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(crearButton, gbc);

        tipoObjetivoComboBox.addActionListener(e -> {
            System.out.println("Accion?");
            String unidad = "km";
            if(tipoObjetivoComboBox.getSelectedItem().toString().equals("Tiempo")) {
                unidad = "min";
            }
            valorObjetivoLabel.setText("Valor objetivo (" + unidad + "):");
        });

        crearButton.addActionListener(e -> crearReto());

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

    private void crearReto() {
        try {
            if(nombreRetoField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "¡El nombre del reto no puede estar vacio!");
                return;
            }

            if(valorObjetivoField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "¡El valor objetivo del reto no puede estar vacio!");
                return;
            }

            if(!deporteRunningCheckbox.isSelected() && !deporteCiclismoCheckbox.isSelected()) {
                JOptionPane.showMessageDialog(this, "¡Marca como minimo un deporte!");
                return;
            }

            RetoNuevoDTO reto = new RetoNuevoDTO();
            reto.setNombre(nombreRetoField.getText());
            reto.setFechaInicio((Date)fechaInicioSpinner.getValue());
            reto.setFechaFin((Date)fechaFinSpinner.getValue());
            reto.setValorObjetivo(Integer.valueOf(valorObjetivoField.getText()));
            if(tipoObjetivoComboBox.getSelectedItem().toString().equals("Distancia")) {
                reto.setTipoObjetivo(TipoObjetivo.Distancia);
            } else {
                reto.setTipoObjetivo(TipoObjetivo.Tiempo);
            }

            ArrayList<Deporte> deportes = new ArrayList<>();
            if(deporteRunningCheckbox.isSelected()) {
                deportes.add(Deporte.Running);
            }
            if(deporteCiclismoCheckbox.isSelected()) {
                deportes.add(Deporte.Ciclismo);
            }

            reto.setDeporte(deportes);

            Controlador.getInstance().crearReto(reto);
            JOptionPane.showMessageDialog(this, "Reto creado exitosamente.");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al crear el reto: " + e.getCause());
        }
    }
}
