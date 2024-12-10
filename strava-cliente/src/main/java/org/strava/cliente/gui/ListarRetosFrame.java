package org.strava.cliente.gui;

import org.strava.cliente.Controlador;
import org.strava.cliente.gui.FormularioExterno.Login.FormularioExternoLogin;
import org.strava.cliente.gui.FormularioExterno.Login.FormularioExternoLoginGoogle;
import org.strava.server.Data.DTO.RetoDTO;
import org.strava.server.Data.DTO.TokenDTO;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListarRetosFrame extends JFrame {
    private TokenDTO tokenSesion;

    public ListarRetosFrame(TokenDTO tokenSesion) {
        this.tokenSesion = tokenSesion;

        setTitle("Lista de Retos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Todos los retos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        List<RetoDTO> retos = obtenerRetos();

        JPanel retosPanel = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        retosPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        int fila = 0;
        int columna = 0;
        final int NUM_COLUMNAS = 2;
        for (RetoDTO reto : retos) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = columna;
            gbc.gridy = fila;
            gbc.weightx = 1.0;

            columna++;
            if(columna == NUM_COLUMNAS) {
                fila += 1;
                columna = 0;
            }


            JPanel panelReto = new JPanel();
            panelReto.setLayout(new BorderLayout());
            panelReto.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            boolean isAuthor = true;

            JLabel nombreLabel = new JLabel(reto.getNombre());
            nombreLabel.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel autorLabel = new JLabel(isAuthor ? "Creado por ti" : "Creado por " + reto.getAutor());
            autorLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            autorLabel.setForeground(Color.GRAY);


            JLabel fechaLabel = new JLabel("una fecha");
            fechaLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            JLabel objetivoLabel = new JLabel("Objetivo: " + reto.getValorObjetivo() + " " + reto.getTipoObjetivo());
            objetivoLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            JLabel deportesLabel = new JLabel("Deportes: " + "asd");
            deportesLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            JButton aceptarRetoButton = new JButton("Aceptar reto");
            aceptarRetoButton.setBackground(Color.BLUE);
            aceptarRetoButton.setForeground(Color.WHITE);

            JPanel panelContenidoReto = new JPanel();
            panelContenidoReto.setBackground(Color.LIGHT_GRAY);
            panelContenidoReto.setLayout(new BoxLayout(panelContenidoReto, BoxLayout.Y_AXIS));
            panelContenidoReto.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelReto.getPreferredSize().height));
            panelContenidoReto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            panelContenidoReto.add(nombreLabel);
            panelContenidoReto.add(autorLabel);
            panelContenidoReto.add(fechaLabel);
            panelContenidoReto.add(objetivoLabel);
            panelContenidoReto.add(deportesLabel);

            JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            panelBoton.setBackground(Color.LIGHT_GRAY);
            panelBoton.add(aceptarRetoButton);

            panelReto.add(panelContenidoReto, BorderLayout.NORTH);
            panelReto.add(panelBoton, BorderLayout.SOUTH);


            retosPanel.add(panelReto, gbc);
        }


        JScrollPane scrollPane = new JScrollPane(retosPanel);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setBackground(Color.BLUE);
        panelPrincipal.add(botonVolver, BorderLayout.SOUTH);
        add(panelPrincipal);

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

    private List<RetoDTO> obtenerRetos() {
        try {
            // List<RetoDTO> retos = new ArrayList<RetoDTO>();
            List<RetoDTO> rs = Controlador.getInstance().conseguirRetosActivos(new Date());
            return rs;
            // for(int i = 0; i < 3; i++ ) {
            //     for (RetoDTO r : rs) {
            //         retos.add(r);
            //     }
            // }
            // return retos;
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al listar retos: " + e.getCause());
        }
        return null;
    }
}
