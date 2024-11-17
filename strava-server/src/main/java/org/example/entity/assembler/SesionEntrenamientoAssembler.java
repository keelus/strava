package org.example.entity.assembler;

import org.example.entity.domain.SesionEntrenamientoDO;
import org.example.entity.dto.SesionEntrenamientoDTO;

public class SesionEntrenamientoAssembler {
    public static SesionEntrenamientoDTO doToDto(SesionEntrenamientoDO sesionEntrenamientoDo) {
        SesionEntrenamientoDTO sesionEntrenamientoDto= new SesionEntrenamientoDTO();

        sesionEntrenamientoDto.setId(sesionEntrenamientoDo.id);
        sesionEntrenamientoDto.setTitulo(sesionEntrenamientoDo.titulo);

        // TODO: Hacer estas
        // retoDto.setAutor(UsuarioAssembler.assemble(reto.autor));
        // retoDto.setDeporte(reto.deporte);
        // retoDto.setObjetivo(reto.objetivo);
        // retoDto.setTipoObjetivo(reto.tipoObjetivo);
        // retoDto.setFechaFin(reto.fechaFin);
        // retoDto.setFechaInicio(reto.fechaInicio);

        return sesionEntrenamientoDto;
    }
    public static SesionEntrenamientoDO dtoToDo(SesionEntrenamientoDTO sesionEntrenamientoDto) {
        SesionEntrenamientoDO sesionEntrenamientoDo = new SesionEntrenamientoDO();

        sesionEntrenamientoDo.setTitulo(sesionEntrenamientoDto.titulo);
        sesionEntrenamientoDo.setId(sesionEntrenamientoDto.id);

        // TODO: Hacer estas
        // retoDo.setAutor(UsuarioAssembler.getDO(retoDto.autor));
        // retoDo.setDeporte(retoDto.deporte);
        // retoDo.setObjetivo(retoDto.objetivo);
        // retoDo.setTipoObjetivo(retoDto.tipoObjetivo);
        // retoDo.setFechaFin(retoDto.fechaFin);
        // retoDo.setFechaInicio(retoDto.fechaInicio);

        return sesionEntrenamientoDo;
    }
}
