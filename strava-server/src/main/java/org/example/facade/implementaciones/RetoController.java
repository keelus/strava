package org.example.facade.implementaciones;

import org.example.entity.assembler.RetoAssembler;
import org.example.entity.assembler.TokenAssembler;
import org.example.entity.dto.RetoDTO;
import org.example.entity.dto.TokenDTO;
import org.example.facade.interfaces.IRetoController;
import org.example.service.RetoService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

// Aqui irian las rutas/metodos que se exportan
public class RetoController extends UnicastRemoteObject implements IRetoController {
    private final RetoService retoService = RetoService.getInstance();

    public RetoController() throws RemoteException {
        super();
    }

    @Override
    public void crearReto(TokenDTO tokenDto, RetoDTO retoDto) throws RemoteException {
        try {
            retoService.crearReto(TokenAssembler.dtoToDo(tokenDto), RetoAssembler.dtoToDo(retoDto));
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void aceptarReto(TokenDTO tokenDto, Long retoId) throws RemoteException {
        try {
            retoService.aceptarReto(TokenAssembler.dtoToDo(tokenDto), retoId);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List<RetoDTO> getRetosActivos(TokenDTO tokenDto, Date fechaLimite) throws RemoteException {
        // List<RetoDTO> retosDto = new ArrayList<>();
        // retoService.getRetos().forEach(reto -> retosDto.add(RetoAssembler.assemble(reto)));
        // return retosDto;
        return List.of();
    }
}
