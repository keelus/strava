package org.strava.cliente.gui;

import org.strava.cliente.Controlador;
import org.strava.server.Data.DTO.RetoDTO;
import org.strava.server.Data.DTO.TokenDTO;
import org.strava.server.Data.Enums.Deporte;
import org.strava.server.Data.Enums.TipoObjetivo;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListarRetosActivosFrame extends JFrame {
    private TokenDTO tokenSesion;

    public ListarRetosActivosFrame(TokenDTO tokenSesion) {
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

        List<RetoDTO> retos = obtenerRetosActivos();

        JPanel retosPanel = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        retosPanel.setLayout(layout);

        int fila = 0;
        int columna = 0;
        final int NUM_COLUMNAS = 2;

        Long usuarioId;
        try {
            usuarioId = Controlador.getInstance().conseguirUsuarioIdSesionActual();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al obtener tu ID de usuario: " + e.getCause());
            dispose();
            return;
        }

        for (RetoDTO reto : retos) {
            RetoElemento retoElemento = elementoReto(reto, usuarioId, fila, columna);
            retosPanel.add(retoElemento.panel, retoElemento.gbc);

            columna++;
            if(columna == NUM_COLUMNAS) {
                fila += 1;
                columna = 0;
            }
        }


        JScrollPane scrollPane = new JScrollPane(retosPanel);
        scrollPane.setBorder(null);
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

    private RetoElemento elementoReto(RetoDTO reto, Long idUsuarioActual, int fila, int columna) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = columna;
        gbc.gridy = fila;
        gbc.weightx = 1.0;

        gbc.insets = new Insets(0, 0, 10, 0);
        if(columna == 0) {
            gbc.insets.right = 5;
        } else {
            gbc.insets.left = 5;
        }


        JPanel panelReto = new JPanel();
        panelReto.setLayout(new BorderLayout());
        panelReto.setBackground(Color.LIGHT_GRAY);
        panelReto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JLabel nombreLabel = new JLabel(reto.getNombre());
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 18));

        boolean esAutor = reto.getAutor().getId().equals(idUsuarioActual);
        JLabel autorLabel = new JLabel(esAutor ? "Creado por ti" : "Creado por " + reto.getAutor().getNombre());
        autorLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        autorLabel.setForeground(Color.GRAY);


        SimpleDateFormat fechaInicio = new SimpleDateFormat("d/M/yyyy");
        String fechaInicioVisual = fechaInicio.format(reto.getFechaInicio());
        SimpleDateFormat fechaFin = new SimpleDateFormat("d/M/yyyy");
        String fechaFinVisual = fechaFin.format(reto.getFechaFin());

        JLabel fechaLabel = new JLabel(fechaInicioVisual + " - " + fechaFinVisual);
        fechaLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        String objetivoUnidad = "km";
        if(reto.getTipoObjetivo() == TipoObjetivo.Tiempo) {
            objetivoUnidad = "min";
        }
        JLabel objetivoLabel = new JLabel("Objetivo: " + reto.getValorObjetivo() + objetivoUnidad +  ", " + reto.getTipoObjetivo());
        objetivoLabel.setFont(new Font("Arial", Font.BOLD, 12));


        JPanel panelCategoria = new JPanel();
        panelCategoria.setBackground(null);
        // panelCategoria.setBackground(Color.GREEN);
        panelCategoria.setLayout(new FlowLayout());
        for(Deporte deporte : reto.getDeporte()) {
            String iconoArchivo = "/ciclismo.png";
            if(deporte.equals(Deporte.Running))
                iconoArchivo = "/running.png";
            JLabel iconoCategoriaLabel = Utils.crearLabelImagen(getClass().getResource(iconoArchivo), 1.0f/10f);
            iconoCategoriaLabel.setPreferredSize(new Dimension(25, 23));

            panelCategoria.add(iconoCategoriaLabel);
        }

        JPanel panelContenidoReto = new JPanel();
        panelContenidoReto.setBackground(null);
        panelContenidoReto.setLayout(new BoxLayout(panelContenidoReto, BoxLayout.Y_AXIS));
        panelContenidoReto.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelReto.getPreferredSize().height));
        panelContenidoReto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelContenidoReto.add(nombreLabel);
        panelContenidoReto.add(autorLabel);
        panelContenidoReto.add(fechaLabel);
        panelContenidoReto.add(objetivoLabel);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(null);
        if(!reto.isAceptadoPorUsuario()) {
            JButton aceptarRetoBoton = new JButton("Aceptar reto");
            aceptarRetoBoton.setBackground(Color.BLUE);
            aceptarRetoBoton.setForeground(Color.WHITE);
            aceptarRetoBoton.addActionListener(e -> {
                reto.setAceptadoPorUsuario(true);
                panelInferior.remove(aceptarRetoBoton);

                JLabel mensajeAceptado = new JLabel("Ya aceptado");
                mensajeAceptado.setFont(new Font("Arial", Font.ITALIC, 10));
                mensajeAceptado.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
                panelInferior.add(mensajeAceptado, BorderLayout.EAST);
                try {
                    Controlador.getInstance().aceptarReto(reto);
                    System.out.println("Ok.");
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

                panelInferior.revalidate();
                panelInferior.repaint();
            });

            panelInferior.add(aceptarRetoBoton, BorderLayout.EAST);
        } else {
            JLabel mensajeAceptado = new JLabel("Ya aceptado");
            mensajeAceptado.setFont(new Font("Arial", Font.ITALIC, 10));
            mensajeAceptado.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
            panelInferior.add(mensajeAceptado, BorderLayout.EAST);
        }
        panelInferior.add(panelCategoria, BorderLayout.WEST);

        panelReto.add(panelContenidoReto, BorderLayout.NORTH);
        panelReto.add(panelInferior, BorderLayout.SOUTH);


        RetoElemento retoElemento = new RetoElemento();
        retoElemento.panel = panelReto;
        retoElemento.gbc = gbc;

        return retoElemento;
    }

    class RetoElemento {
        public JPanel panel;
        public GridBagConstraints gbc;
    }

    private List<RetoDTO> obtenerRetosActivos() {
        try {
            // Mirar: Es un filtro o es siempre al momento de la busquda?

            Date fecha = new Date();
            Date fechaCorrecta = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDate(), 0, 0, 0);
            List<RetoDTO> rs = Controlador.getInstance().conseguirRetosActivos(fechaCorrecta);
            return rs;
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al listar retos: " + e.getCause());
        }
        return null;
    }
}
