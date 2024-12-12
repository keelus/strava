package com.meta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MetaService extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket tcpSocket;
    private PersistenciaUsuario usuarios;

    MetaService(Socket socket, PersistenciaUsuario usuarios) {
        try {
            this.tcpSocket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.usuarios = usuarios;
            this.start();
        } catch (Exception e) {
            System.out.println("MetaService - TCPConnection IO error:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            //Leer peticion
            String contenido = this.in.readUTF();
            System.out.println("MetaService - Datos recibidos de '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + contenido + "'");

            Usuario usuarioObtenido = new Usuario(contenido);

            if (usuarios.existeUsuario(usuarioObtenido.getEmail())
                    && usuarios.getContrasena(usuarioObtenido.getEmail()).equals(usuarioObtenido.getContrasena())) {
                    this.out.writeUTF("OK");
            } else {
                this.out.writeUTF("Las credenciales introducidas no son correctas, o el email no existe.");
            }

            System.out.println("MetaService - Datos enviados a '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + usuarioObtenido.toString() + "'");
        } catch (Exception e) {
            System.out.println("MetaService error: " + e.getMessage());
        } finally {
            try {
                tcpSocket.close();
            } catch (Exception e) {
                System.out.println("MetaService error: " + e.getMessage());
            }
        }
    }
}