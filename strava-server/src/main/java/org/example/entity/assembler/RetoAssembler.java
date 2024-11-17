package org.example.entity.assembler;

import org.example.entity.dto.RetoDTO;
import org.example.entity.domain.RetoDO;

public class RetoAssembler {
    public static RetoDTO doToDto(RetoDO reto) {
        RetoDTO retoDto = new RetoDTO();

        retoDto.setId(reto.id);
        retoDto.setNombre(reto.nombre);

        // TODO: Hacer estas
        // retoDto.setAutor(UsuarioAssembler.assemble(reto.autor));
        // retoDto.setDeporte(reto.deporte);
        // retoDto.setObjetivo(reto.objetivo);
        // retoDto.setTipoObjetivo(reto.tipoObjetivo);
        // retoDto.setFechaFin(reto.fechaFin);
        // retoDto.setFechaInicio(reto.fechaInicio);

        return retoDto;
    }
    public static RetoDO dtoToDo(RetoDTO retoDto) {
        RetoDO retoDo = new RetoDO();

        retoDo.setNombre(retoDto.nombre);
        retoDo.setId(retoDto.id);

        // TODO: Hacer estas
        // retoDo.setAutor(UsuarioAssembler.getDO(retoDto.autor));
        // retoDo.setDeporte(retoDto.deporte);
        // retoDo.setObjetivo(retoDto.objetivo);
        // retoDo.setTipoObjetivo(retoDto.tipoObjetivo);
        // retoDo.setFechaFin(retoDto.fechaFin);
        // retoDo.setFechaInicio(retoDto.fechaInicio);

        return retoDo;
    }
}
