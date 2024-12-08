package org.strava.server.AutenticacionGateway;

import org.strava.server.Data.Dominio.LoginCredencialesDO;
import org.strava.server.Data.Enums.MetodoRegistro;
import org.strava.server.Servicios.ServicioReto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class AutenticacionGateway {
    private static AutenticacionGateway instance;

    private AutenticacionGateway() {
    }

    public static AutenticacionGateway getInstance() {
        if(instance == null) {
            instance = new AutenticacionGateway();
        }
        return instance;
    }

    public void loginServicioExterno(LoginCredencialesDO loginCredencialesDo, MetodoRegistro metodoRegistro) throws Exception {
        switch(metodoRegistro) {
            case Google: {
                String urlString = "http://localhost:8080/user/login";
                URL urlObj = new URL(urlString);

                HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                String contenido = "{"
                        + "\"email\":\"" + loginCredencialesDo.getEmail() + "\","
                        + "\"contrasenya\":\"" + loginCredencialesDo.getContrasenya() + "\""
                        + "}";

                // Mandar cuerpo con la solicitud
                try (OutputStream os = con.getOutputStream()) {
                    byte[] contenido_bytes = contenido.getBytes("utf-8");
                    os.write(contenido_bytes, 0, contenido_bytes.length);
                }

                // Conseguir y leer la respuesta
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    return;
                } else if (con.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    throw new Exception("Google dice: \"" + response.toString() + "\"");
                } else {
                    throw new Exception("Ha ocurrido un error inesperado al comunicarse con \"Google\". Intentalo mas tarde.");
                }
            }
            case Meta: {
                String serverIP = "0.0.0.0";
                int serverPort = 444;
                String usuario = loginCredencialesDo.getEmail() + ";" + loginCredencialesDo.getContrasenya();

                try (Socket tcpSocket = new Socket(serverIP, serverPort);
                     DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())){

                    //enviar datos al server
                    out.writeUTF(usuario);
                    System.out.println("MetaClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + usuario + "'");

                    //recibir respuesta del server
                    String data = in.readUTF();
                    System.out.println("MetaClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
                    if (data.equals("OK")){
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("MetaSocketError: " + e.getMessage());
                }
                break;
            }
        }

        throw new Exception("Ha ocurrido un error inesperado al comunicarse con \"" + metodoRegistro.toString() + "\". Intentalo mas tarde.");
    }
}
