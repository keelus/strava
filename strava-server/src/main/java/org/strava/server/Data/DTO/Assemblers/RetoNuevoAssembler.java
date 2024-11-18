package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.DTO.RetoNuevoDTO;
import org.strava.server.Data.Dominio.RetoNuevoDO;

public class RetoNuevoAssembler {
    public static RetoNuevoDTO doToDto(RetoNuevoDO retoNuevoDo) throws Exception{
        RetoNuevoDTO retoNuevoDto = new RetoNuevoDTO();

        retoNuevoDto.setNombre(retoNuevoDo.getNombre());
        retoNuevoDto.setFechaInicio(retoNuevoDo.getFechaInicio());
        retoNuevoDto.setFechaFin(retoNuevoDo.getFechaFin());
        retoNuevoDto.setTipoObjetivo(retoNuevoDo.getTipoObjetivo());
        retoNuevoDto.setValorObjetivo(retoNuevoDo.getValorObjetivo());
        retoNuevoDto.setDeporte(retoNuevoDo.getDeporte());

        return retoNuevoDto;
    }
    public static RetoNuevoDO dtoToDo(RetoNuevoDTO retoNuevoDto) {
        RetoNuevoDO retoDo = new RetoNuevoDO();

        retoDo.setNombre(retoNuevoDto.getNombre());
        retoDo.setFechaInicio(retoNuevoDto.getFechaInicio());
        retoDo.setFechaFin(retoNuevoDto.getFechaFin());
        retoDo.setTipoObjetivo(retoNuevoDto.getTipoObjetivo());
        retoDo.setValorObjetivo(retoNuevoDto.getValorObjetivo());
        retoDo.setDeporte(retoNuevoDto.getDeporte());

        return retoDo;
    }
}
