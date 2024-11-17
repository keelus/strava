package org.example.facade.implementaciones;

import org.example.entity.assembler.SesionEntrenamientoAssembler;
import org.example.entity.assembler.TokenAssembler;
import org.example.entity.domain.SesionEntrenamientoDO;
import org.example.entity.domain.TokenDO;
import org.example.entity.dto.SesionEntrenamientoDTO;
import org.example.entity.dto.TokenDTO;
import org.example.facade.interfaces.ISesionEntrenamientoController;
import org.example.service.SesionEntrenamientoService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Aqui irian las rutas/metodos que se exportan
public class SesionEntrenamientoController extends UnicastRemoteObject implements ISesionEntrenamientoController {
    private final SesionEntrenamientoService sesionEntrenamientoService = SesionEntrenamientoService.getInstance();

    public SesionEntrenamientoController() throws RemoteException {
        super();
    }

    @Override
    public void crearSesionEntrenamiento(TokenDTO tokenDto, SesionEntrenamientoDTO sesionEntrenamientoDto) throws RemoteException {
        TokenDO tokenDo = TokenAssembler.dtoToDo(tokenDto);
        SesionEntrenamientoDO sesionEntrenamientoDo = SesionEntrenamientoAssembler.dtoToDo(sesionEntrenamientoDto);
        try {
            sesionEntrenamientoService.crearSesionEntrenamiento(tokenDo, sesionEntrenamientoDo);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
