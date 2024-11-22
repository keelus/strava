package org.strava.cliente.gui;

import org.strava.cliente.Controlador;
import org.strava.server.Data.DTO.RetoDTO;
import org.strava.server.Data.DTO.TokenDTO;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class ListarRetosFrame extends JFrame {
    private TokenDTO tokenSesion;

    public ListarRetosFrame(TokenDTO tokenSesion) {
        this.tokenSesion = tokenSesion;

        setTitle("Lista de Retos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(45, 52, 54));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // lista de retos
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Roboto", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setBackground(new Color(39, 43, 48));
        textArea.setForeground(Color.WHITE);

        // scroll para la lista
        JScrollPane scrollPane = new JScrollPane(textArea);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        mainPanel.add(scrollPane, gbc);

        // boton de actualizar
        JButton actualizarButton = new JButton("Actualizar");
        Utils.estilarButton(actualizarButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 2;
        mainPanel.add(actualizarButton, gbc);

        actualizarButton.addActionListener(e -> listarRetos(textArea));

        add(mainPanel);
        listarRetos(textArea);
        setVisible(true);
    }

    private void listarRetos(JTextArea textArea) {
        try {
            List<RetoDTO> retosObtenidos = Controlador.getInstance().conseguirRetosActivos(new Date());
            textArea.setText("");
            retosObtenidos.forEach(reto ->
                    textArea.append(
                            "RETO " + reto.getId() + "\n"
                            + "Nombre: " + reto.getNombre() + "\n"
                            + "Autor: " + reto.getAutor().getNombre() + "\n"
                            + "Deporte: " + reto.getDeporte() + "\n"
                            + "Tipo Objetivo: " + reto.getTipoObjetivo() + "\n"
                            + "Valor Objetivo: " + reto.getValorObjetivo() + "\n"
                            + "Inicio: " + reto.getFechaInicio() + "\n"
                            + "Fin: " + reto.getFechaFin() + "\n"
                            + "\n"));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al listar retos: " + e.getCause());
        }
    }
}
