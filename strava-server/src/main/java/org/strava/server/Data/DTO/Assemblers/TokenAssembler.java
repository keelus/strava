package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.Dominio.TokenDO;
import org.strava.server.Data.DTO.TokenDTO;

public class TokenAssembler {
    public static TokenDTO doToDto(TokenDO tokenDo) {
        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setValor(tokenDo.getValor());
        return tokenDto;
    }
    public static TokenDO dtoToDo(TokenDTO tokenDto) {
        TokenDO tokenDo = new TokenDO(tokenDto.getValor());
        return tokenDo;
    }
}
