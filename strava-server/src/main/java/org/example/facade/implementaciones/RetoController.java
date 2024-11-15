package org.example.facade.implementaciones;

import org.example.entity.assembler.RetoAssembler;
import org.example.entity.domain.RetoDO;
import org.example.entity.dto.RetoDTO;
import org.example.facade.interfaces.IRetoController;
import org.example.service.RetoService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Aqui irian las rutas/metodos que se exportan
public class RetoController extends UnicastRemoteObject implements IRetoController {

    private final RetoService retoService = new RetoService();

    public RetoController() throws RemoteException {
        super();
    }

    @Override
    public List<RetoDTO> getRetos() throws RemoteException {
        List<RetoDTO> retosDto = new ArrayList<>();
        retoService.getRetos().forEach(reto -> retosDto.add(RetoAssembler.assemble(reto)));
        return retosDto;
    }

    @Override
    public RetoDTO getReto(Long id) throws RemoteException {
        Optional<RetoDO> reto = retoService.getReto(id);

        return RetoAssembler.assemble(
                reto.orElseThrow(() ->
                        new RemoteException("El reto con la ID \"" + id.toString() + "\" no existe.")
                )
        );
    }

    @Override
    public void crearReto(RetoDTO retoDto) throws RemoteException {
        retoService.crearReto(retoDto);
    }
}
