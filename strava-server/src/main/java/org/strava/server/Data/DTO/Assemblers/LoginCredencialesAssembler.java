package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.Dominio.LoginCredencialesDO;
import org.strava.server.Data.DTO.LoginCredencialesDTO;

public class LoginCredencialesAssembler  {
    public static LoginCredencialesDTO doToDto(LoginCredencialesDO credencialesDo) {
        LoginCredencialesDTO credencialesDto = new LoginCredencialesDTO();

        credencialesDto.setEmail(credencialesDo.getEmail());
        credencialesDto.setContrasenya(credencialesDo.getContrasenya());

        return credencialesDto;
    }
    public static LoginCredencialesDO dtoToDo(LoginCredencialesDTO credencialesDto) {
        LoginCredencialesDO credencialesDo = new LoginCredencialesDO();

        credencialesDo.setEmail(credencialesDto.getEmail());
        credencialesDo.setContrasenya(credencialesDto.getContrasenya());

        return credencialesDo;
    }
}
