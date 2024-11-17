package org.example.entity.assembler;

import org.example.entity.domain.LoginCredencialesDO;
import org.example.entity.dto.LoginCredencialesDTO;

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
