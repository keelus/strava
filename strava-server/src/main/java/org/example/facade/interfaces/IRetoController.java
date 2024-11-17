package org.example.facade.interfaces;

import org.example.entity.domain.RetoDO;
import org.example.entity.dto.RetoDTO;
import org.example.entity.dto.TokenDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IRetoController extends Remote {
    public void crearReto(TokenDTO tokenDto, RetoDTO retoDto) throws RemoteException;
    public void aceptarReto(TokenDTO tokenDto, Long retoId) throws RemoteException;
    public List<RetoDTO> getRetosActivos(TokenDTO tokenDto, Date fechaLimite) throws RemoteException;
}
