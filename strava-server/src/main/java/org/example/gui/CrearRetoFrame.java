package org.example.gui;

import org.example.Cliente;
import org.example.entity.dto.RetoDTO;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class CrearRetoFrame extends JFrame {

    private JTextField nombreRetoField;

    public CrearRetoFrame() {
        setTitle("Crear Reto");
        setSize(400, 200);
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

        // boton crear reto
        JButton crearButton = new JButton("Crear");
        estilarButton(crearButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(crearButton, gbc);

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
            RetoDTO reto = new RetoDTO();
            reto.setNombre(nombreRetoField.getText());

            Cliente.retoController.crearReto(reto);
            JOptionPane.showMessageDialog(this, "Reto creado exitosamente.");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al crear el reto: " + e.getCause());
        } finally {
            nombreRetoField.setText("");
        }
    }
}
