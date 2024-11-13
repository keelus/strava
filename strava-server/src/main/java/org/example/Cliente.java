package org.example;

import org.example.interfaces.ServicioReto;
import org.example.interfaces.ServicioUsuario;
import org.example.modelos.LoginCredenciales;
import org.example.modelos.Reto;
import org.example.modelos.RetoListarActivosCuerpo;
import org.example.modelos.Usuario;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// meto el cliente aqui porque necesita las interfaces y los modelos, deberiamos copiarlos en el strava-cliente?
public class Cliente {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            ServicioUsuario usuarioService = (ServicioUsuario) registry.lookup("usuarioService");
            ServicioReto retoService = (ServicioReto) registry.lookup("retoService");

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setEmail("email@gmail.com");
            nuevoUsuario.setFechaNacimiento(new Date());
            nuevoUsuario.setNombre("Pepe");
            nuevoUsuario.setId(999); // Esto deberia de hacerlo el servidor

            try {
                usuarioService.registrar(nuevoUsuario); // Registramos el usuario
                System.out.println("Registro del usuario exitoso.");
            } catch(RemoteException e) {
                System.out.println("Registro no tiene que fallar(la primera vez): " + e.getCause());
            }

            try {
                usuarioService.registrar(nuevoUsuario); // Intentamos registrar otro usuario con el mismo email
            } catch(RemoteException e) {
                System.out.println("Registro tiene que fallar: " + e.getCause());
            }

            // Iniciamos con el usuario nuevo creado
            LoginCredenciales credenciales = new LoginCredenciales();
            credenciales.setEmail("email@gmail.com");
            credenciales.setContrasenya("loquesea");

            try {
                Usuario usuario = usuarioService.login(credenciales);
                System.out.println("Login del usuario exitoso.");
            } catch(RemoteException e) {
                // No fallara
            }

            try {
                credenciales.setEmail("esteNoExiste@gmail.com"); // Fallara, no existe
                Usuario usuario2 = usuarioService.login(credenciales);
            } catch(RemoteException e) {
                System.out.println("Login tiene que fallar: " + e.getCause());
            }

            // Aqui faltaria pasarle el Usuario que lo crea.
            ArrayList<String> nombresRetosNuevos = new ArrayList<String>(Arrays.asList("Caminata", "Ruta mañanera", "Senderismo a la tarde", "Sprint"));
            nombresRetosNuevos.forEach(nombre -> {
                Reto reto = new Reto();
                reto.setNombre(nombre);
                try {
                    retoService.crearReto(reto);
                } catch (RemoteException e) {
                    System.out.println("No deberia fallar: " + e.getCause());
                }
            });

            // Una vez creados los retos, los conseguimos del servidor
            try {
                RetoListarActivosCuerpo cuerpo = new RetoListarActivosCuerpo();
                cuerpo.fecha = new Date().toString();
                List<Reto> retosCreados = retoService.listarRetosActivos(cuerpo);
                System.out.println("== Retos creados ==");
                retosCreados.forEach(reto -> System.out.println(reto.getNombre()));
            } catch(RemoteException e) {
                System.out.println("No deberia fallar: " + e.getCause());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
