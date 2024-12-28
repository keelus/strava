package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.DTO.RetoDTO;
import org.strava.server.Data.Dominio.RetoDO;
import org.strava.server.Data.Dominio.UsuarioDO;
import org.strava.server.Servicios.ServicioAutenticacion;

public class RetoAssembler {
    public static RetoDTO doToDto(RetoDO reto, boolean aceptadoPorUsuario) throws Exception{
        RetoDTO retoDto = new RetoDTO();

        retoDto.setId(reto.getId());
        retoDto.setNombre(reto.getNombre());
        retoDto.setAutor(UsuarioAssembler.doToDto(reto.getAutor()));
        retoDto.setDeporte(reto.getDeporte());
        retoDto.setValorObjetivo(reto.getValorObjetivo());
        retoDto.setTipoObjetivo(reto.getTipoObjetivo());
        retoDto.setFechaInicio(reto.getFechaInicio());
        retoDto.setFechaFin(reto.getFechaFin());
        retoDto.setAceptadoPorUsuario(aceptadoPorUsuario);

        return retoDto;
    }
    public static RetoDO dtoToDo(RetoDTO retoDto) {
        RetoDO retoDo = new RetoDO();

        retoDo.setNombre(retoDto.getNombre());
        retoDo.setId(retoDto.getId());
        retoDo.setAutor(UsuarioAssembler.dtoToDo(retoDto.getAutor()));
        retoDo.setDeporte(retoDto.getDeporte());
        retoDo.setValorObjetivo(retoDto.getValorObjetivo());
        retoDo.setTipoObjetivo(retoDto.getTipoObjetivo());
        retoDo.setFechaInicio(retoDto.getFechaInicio());
        retoDo.setFechaFin(retoDto.getFechaFin());

        return retoDo;
    }
}
