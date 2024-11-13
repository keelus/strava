package org.example.interfaces;

import org.example.modelos.Reto;
import org.example.modelos.RetoAceptarCuerpo;
import org.example.modelos.RetoListarActivosCuerpo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServicioReto extends Remote {
    String crearReto(Reto reto) throws RemoteException;
    String aceptarReto(RetoAceptarCuerpo cuerpo) throws RemoteException;
    List<Reto> listarRetosActivos(RetoListarActivosCuerpo cuerpo) throws RemoteException;
}
