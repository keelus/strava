package org.strava.server.AutenticacionGateway;

import org.strava.server.Data.Dominio.LoginCredencialesDO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleGateway implements IAutenticacionGateway {

    @Override
    public void iniciarSesion(LoginCredencialesDO loginCredencialesDo) throws Exception {
        String urlString = "http://localhost:4445/user/login";
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
}
