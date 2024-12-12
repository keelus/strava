package org.strava.server.AutenticacionGateway;

import org.strava.server.Data.Dominio.LoginCredencialesDO;
import org.strava.server.Data.Enums.MetodoRegistro;

public interface IAutenticacionGateway {
    public void iniciarSesion(LoginCredencialesDO loginCredencialesDo) throws Exception;
}
