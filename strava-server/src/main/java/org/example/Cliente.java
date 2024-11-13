package org.example;

import org.example.interfaces.ServicioReto;
import org.example.interfaces.ServicioUsuario;
import org.example.modelos.LoginCredenciales;
import org.example.modelos.Reto;
import org.example.modelos.Usuario;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// meto el cliente aqui porque necesita las interfaces y los modelos, deberiamos copiarlos en el strava-cliente?
public class Cliente {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            ServicioUsuario usuarioService = (ServicioUsuario) registry.lookup("usuarioService");
            ServicioReto retoService = (ServicioReto) registry.lookup("retoService");

            LoginCredenciales credenciales = new LoginCredenciales();
            credenciales.setEmail("test");
            credenciales.setContrasenya("test");

            Usuario usuario = usuarioService.login(credenciales);
            if (usuario != null) {
                System.out.println("Login valido: " + usuario.getNombre());
            } else {
                System.out.println("Login fallido");
            }

            Reto reto = new Reto();
            reto.setNombre("nuevo reto uwu");
            System.out.println(retoService.crearReto(reto));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
