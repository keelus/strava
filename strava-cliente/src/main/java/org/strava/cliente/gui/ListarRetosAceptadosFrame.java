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

public class ListarRetosAceptadosFrame extends JFrame {
    private TokenDTO tokenSesion;

    public ListarRetosAceptadosFrame(TokenDTO tokenSesion) {
        this.tokenSesion = tokenSesion;

        setTitle("Lista de Retos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Retos aceptados por ti");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        List<RetoDTO> retos = obtenerRetosAceptados();

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
        panelInferior.add(panelCategoria, BorderLayout.WEST);

        panelReto.add(panelContenidoReto, BorderLayout.CENTER);
        panelReto.add(panelInferior, BorderLayout.SOUTH);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 1.0;

        // Panel completado
        JPanel panelCompletado = new JPanel(new GridBagLayout());
        panelCompletado.setBackground(null);
        JLabel panelCompletadoTitulo = new JLabel("Completado en un");
        panelCompletadoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelCompletadoTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        int porcentaje = Math.round(reto.getPorcentajeCompletado());
        JLabel panelCompletadoPorcentaje = new JLabel(porcentaje + "%");
        panelCompletadoPorcentaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelCompletadoPorcentaje.setFont(new Font("Arial", Font.BOLD, 24));

        if(porcentaje <= 25) panelCompletadoPorcentaje.setForeground(Color.RED);
        else if(porcentaje <= 50) panelCompletadoPorcentaje.setForeground(Color.ORANGE);
        else if(porcentaje <= 60) panelCompletadoPorcentaje.setForeground(Color.YELLOW);
        else panelCompletadoPorcentaje.setForeground(Color.GREEN);

        panelCompletado.add(panelCompletadoTitulo, gbc2);
        gbc2.insets.top = 10;
        gbc2.gridy = 2;
        panelCompletado.add(panelCompletadoPorcentaje, gbc2);
        panelReto.add(panelCompletado, BorderLayout.EAST);


        RetoElemento retoElemento = new RetoElemento();
        retoElemento.panel = panelReto;
        retoElemento.gbc = gbc;

        return retoElemento;
    }

    class RetoElemento {
        public JPanel panel;
        public GridBagConstraints gbc;
    }

    private List<RetoDTO> obtenerRetosAceptados() {
        try {
            List<RetoDTO> rs = Controlador.getInstance().conseguirRetosAceptados();
            return rs;
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error al listar retos: " + e.getCause());
        }
        return null;
    }
}
