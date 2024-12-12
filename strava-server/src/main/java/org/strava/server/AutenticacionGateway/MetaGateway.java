package org.strava.server.AutenticacionGateway;

import org.strava.server.Data.Dominio.LoginCredencialesDO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class MetaGateway implements IAutenticacionGateway {

    @Override
    public void iniciarSesion(LoginCredencialesDO loginCredencialesDo) throws Exception {
        String serverIP = "0.0.0.0";
        int serverPort = 4446;
        String usuario = loginCredencialesDo.getEmail() + ";" + loginCredencialesDo.getContrasenya();

        try (Socket tcpSocket = new Socket(serverIP, serverPort);
             DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())){

            //enviar datos al server
            out.writeUTF(usuario);

            //recibir respuesta del server
            String data = in.readUTF();
            if(data.equals("OK")) {
                return;
            } else {
                throw new Exception(data);
            }
        } catch (Exception e) {
            throw new Exception("Meta dice: \"" + e.getMessage()+ "\"");
        }
    }
}
