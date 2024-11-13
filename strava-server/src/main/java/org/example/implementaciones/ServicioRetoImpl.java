package org.example.implementaciones;

import org.example.interfaces.ServicioReto;
import org.example.modelos.Reto;
import org.example.modelos.RetoAceptarCuerpo;
import org.example.modelos.RetoListarActivosCuerpo;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ServicioRetoImpl extends UnicastRemoteObject implements ServicioReto {
    ArrayList<Reto> retosCreados = new ArrayList<Reto>();

    public ServicioRetoImpl() throws RemoteException {
        super();
    }

    @Override
    public String crearReto(Reto reto) throws RemoteException {
        retosCreados.add(reto);
        return "Reto creado: " + reto.getNombre();
    }

    @Override
    public String aceptarReto(RetoAceptarCuerpo cuerpo) throws RemoteException {
        return "Reto aceptado por: " + cuerpo.getUsuario().getNombre();
    }

    // donde guardamos las variables :(
    @Override
    public List<Reto> listarRetosActivos(RetoListarActivosCuerpo cuerpo) throws RemoteException {
        return retosCreados;
    }
}
