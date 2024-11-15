package org.example.entity.assembler;

import org.example.entity.domain.RetoDO;
import org.example.entity.domain.TokenDO;
import org.example.entity.dto.RetoDTO;
import org.example.entity.dto.TokenDTO;

public class TokenAssembler {
    public static TokenDTO assemble(TokenDO tokenDo) {
        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setValor(tokenDo.getValor());
        return tokenDto;
    }
    public static TokenDO getDO(TokenDTO tokenDto) {
        TokenDO tokenDo = new TokenDO(tokenDto.getValor());
        return tokenDo;
    }
}
