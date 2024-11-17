package org.strava.cliente;

import javax.swing.*;

import org.strava.cliente.gui.LoginFrame;
import org.strava.server.RemoteFachada.IRemoteFachada;
import org.strava.server.Data.DTO.UsuarioDTO;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
// import org.strava.server.RemoteFachada.IRemoteFachada;

// meto el cliente aqui porque necesita las interfaces y los modelos, deberiamos copiarlos en el strava-cliente?
public class Cliente {
    public static IRemoteFachada remoteFachada;

    public static void main(String[] args) {
        try {
            UsuarioDTO usuarioDto = new UsuarioDTO();
            System.out.println("Hi");
            Registry registry = LocateRegistry.getRegistry("localhost", 4444);

            // // Declarar las interfaces de los controladores facade
            remoteFachada = (IRemoteFachada) registry.lookup("remoteFachada");

            // Iniciar ventana login
            SwingUtilities.invokeLater(LoginFrame::new);
            // ### CREAR USUARIO
//            UsuarioDTO nuevoUsuario = new UsuarioDTO();
//            nuevoUsuario.setEmail("email@gmail.com");
//            nuevoUsuario.setFechaNacimiento(new Date());
//            nuevoUsuario.setNombre("Pepe");
//            nuevoUsuario.setId(999); // Esto deberia de hacerlo el servidor
//
//            // ### REGISTRAR USUARIO (OK)
//            try {
//                authController.registrar(nuevoUsuario); // Registramos el usuario
//                System.out.println("Registro del usuario exitoso.");
//            } catch(RemoteException e) {
//                System.out.println("Registro no tiene que fallar(la primera vez): " + e.getCause());
//            }
//
//            // ### REGISTRAR USUARIO (ERROR)
//            try {
//                authController.registrar(nuevoUsuario); // Intentamos registrar otro usuario con el mismo email
//            } catch(RemoteException e) {
//                System.out.println("Registro tiene que fallar: " + e.getCause());
//            }
//
//            // ### CREDENCIALES PARA EL USUARIO REGISTRADO
//            LoginCredencialesDTO credenciales = new LoginCredencialesDTO();
//            credenciales.setEmail("email@gmail.com");
//            credenciales.setContrasenya("loquesea");
//
//            // ### LOGIN (OK)
//            try {
//                TokenDTO token = authController.login(credenciales);
//                System.out.println("Login del usuario exitoso. Token obtenido: \"" + token.getValor() + "\"");
//            } catch(RemoteException e) {
//                // No fallara
//            }
//
//            // ### LOGIN (ERROR)
//            try {
//                credenciales.setEmail("esteNoExiste@gmail.com"); // Fallara, no existe
//                TokenDTO token = authController.login(credenciales);
//                System.out.println("Login del usuario exitoso. Token obtenido: \"" + token.getValor() + "\"");
//            } catch(RemoteException e) {
//                System.out.println("Login tiene que fallar: " + e.getCause());
//            }
//
//            // ### CREAR RETOS (OK)
//            // TODO: Pasar token para este tipo de llamadas
//            ArrayList<String> nombresRetosNuevos = new ArrayList<String>(Arrays.asList("Caminata", "Ruta mañanera", "Senderismo a la tarde", "Sprint"));
//            nombresRetosNuevos.forEach(nombre -> {
//                RetoDTO reto = new RetoDTO();
//                reto.setNombre(nombre);
//                try {
//                    retoController.crearReto(reto);
//                } catch (RemoteException e) {
//                    System.out.println("No deberia fallar: " + e.getCause());
//                }
//            });
//
//            // ### LISTAR RETOS (OK)
//            // TODO: Pasar token para este tipo de llamadas
//            try {
//                // RetoListarActivosCuerpo cuerpo = new RetoListarActivosCuerpo();
//                // cuerpo.fecha = new Date().toString();
//                List<RetoDTO> retosCreados = retoController.getRetos();
//                System.out.println("== Retos creados ==");
//                retosCreados.forEach(reto -> System.out.println(reto.getNombre()));
//            } catch(RemoteException e) {
//                System.out.println("No deberia fallar: " + e.getCause());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
