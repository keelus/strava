package org.example.entity.assembler;

import org.example.entity.domain.LoginCredencialesDO;
import org.example.entity.domain.TokenDO;
import org.example.entity.dto.LoginCredencialesDTO;
import org.example.entity.dto.TokenDTO;

public class LoginCredencialesAssembler {
    public static LoginCredencialesDTO assemble(LoginCredencialesDO credencialesDo) {
        LoginCredencialesDTO credencialesDto = new LoginCredencialesDTO();

        credencialesDto.setEmail(credencialesDo.getEmail());
        credencialesDto.setContrasenya(credencialesDo.getContrasenya());

        return credencialesDto;
    }
    public static LoginCredencialesDO getDO(LoginCredencialesDTO credencialesDto) {
        LoginCredencialesDO credencialesDo = new LoginCredencialesDO();

        credencialesDo.setEmail(credencialesDto.getEmail());
        credencialesDo.setContrasenya(credencialesDto.getContrasenya());

        return credencialesDo;
    }
}
