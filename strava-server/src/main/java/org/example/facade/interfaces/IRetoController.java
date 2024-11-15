package org.example.facade.interfaces;

import org.example.entity.domain.RetoDO;
import org.example.entity.dto.RetoDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRetoController extends Remote {
    public List<RetoDTO> getRetos() throws RemoteException;
    public RetoDTO getReto(Long id) throws RemoteException;
    public void crearReto(RetoDTO retoDto) throws RemoteException;
}
