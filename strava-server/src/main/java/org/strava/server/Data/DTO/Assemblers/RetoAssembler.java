package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.DTO.RetoDTO;
import org.strava.server.Data.Dominio.RetoDO;
import org.strava.server.Data.Dominio.UsuarioDO;
import org.strava.server.Servicios.AuthService;

public class RetoAssembler {
    public static RetoDTO doToDto(RetoDO reto) throws Exception{
        RetoDTO retoDto = new RetoDTO();

        retoDto.setId(reto.id);
        retoDto.setNombre(reto.nombre);

        // TODO: Hacer estas
        UsuarioDO usuarioDo = AuthService.getInstance().conseguirUsuario(reto.autorId);
        retoDto.setAutor(UsuarioAssembler.doToDto(usuarioDo));
        retoDto.setDeporte(reto.deporte);
        retoDto.setObjetivo(reto.objetivo);
        retoDto.setTipoObjetivo(reto.tipoObjetivo);
        retoDto.setFechaFin(reto.fechaFin);
        retoDto.setFechaInicio(reto.fechaInicio);

        return retoDto;
    }
    public static RetoDO dtoToDo(RetoDTO retoDto) {
        RetoDO retoDo = new RetoDO();

        retoDo.setNombre(retoDto.nombre);
        retoDo.setId(retoDto.id);

        if(retoDto.autor != null) // Prevenir null, pues al crearlo lo asigna el servidor la 1a vez
            retoDo.setAutorId(retoDto.autor.getId());

        retoDo.setDeporte(retoDto.deporte);
        retoDo.setObjetivo(retoDto.objetivo);
        retoDo.setTipoObjetivo(retoDto.tipoObjetivo);
        retoDo.setFechaFin(retoDto.fechaFin);
        retoDo.setFechaInicio(retoDto.fechaInicio);

        return retoDo;
    }
}
