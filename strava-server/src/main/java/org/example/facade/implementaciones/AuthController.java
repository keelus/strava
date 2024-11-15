package org.example.facade.implementaciones;

import org.example.entity.assembler.LoginCredencialesAssembler;
import org.example.entity.assembler.TokenAssembler;
import org.example.entity.assembler.UsuarioAssembler;
import org.example.entity.domain.TokenDO;
import org.example.entity.dto.LoginCredencialesDTO;
import org.example.entity.dto.TokenDTO;
import org.example.entity.dto.UsuarioDTO;
import org.example.facade.interfaces.IAuthController;
import org.example.service.AuthService;
import org.example.service.RetoService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

public class AuthController extends UnicastRemoteObject implements IAuthController {

    private final AuthService authService = new AuthService();

    public AuthController() throws RemoteException {
        super();
    }

    @Override
    public void registrar(UsuarioDTO usuarioDto) throws RemoteException {
        try {
            authService.registrarUsuario(UsuarioAssembler.getDO(usuarioDto));
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public TokenDTO login(LoginCredencialesDTO credencialesDto) throws RemoteException {
        Optional<TokenDO> token = authService.crearSesion(LoginCredencialesAssembler.getDO(credencialesDto));

        return TokenAssembler.assemble(
                token.orElseThrow(() -> new RemoteException(
                        "Las credenciales son incorrectas o el usuario no existe."
                ))
        );
    }

    @Override
    public void logout(TokenDTO tokenDto) throws RemoteException {
        authService.borrarSesion(TokenAssembler.getDO(tokenDto));
    }
}
