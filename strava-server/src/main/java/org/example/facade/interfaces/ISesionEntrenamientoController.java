package org.example.facade.interfaces;

import org.example.entity.dto.RetoDTO;
import org.example.entity.dto.SesionEntrenamientoDTO;
import org.example.entity.dto.TokenDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ISesionEntrenamientoController extends Remote {
    public void crearSesionEntrenamiento(TokenDTO tokenDto, SesionEntrenamientoDTO sesionEntrenamientoDto) throws RemoteException;
}
