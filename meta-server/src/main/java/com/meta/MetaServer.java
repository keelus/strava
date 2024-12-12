package com.meta;

import java.net.ServerSocket;

public class MetaServer {

    public static void main(String[] args) {
        int serverPort = 4446;
        PersistenciaUsuario usuarios = new PersistenciaUsuario();
        usuarios.anadirUsuario("usuario1@meta.com", "1234");
        usuarios.anadirUsuario("usuario2@meta.com", "1234");

        try (ServerSocket tcpMetaServerSocket = new ServerSocket(serverPort)) {
            System.out.println("MetaServer: Esperando conexion '" + tcpMetaServerSocket.getInetAddress().getHostAddress() + ":" + serverPort + "' ...");

            while (true) {
                new MetaService(tcpMetaServerSocket.accept(), usuarios);
                System.out.println("MetaServer: conexion con cliente aceptada");
            }
        } catch (Exception e) {
            System.out.println("MetaServer: IO error:" + e.getMessage());
        }
    }
}