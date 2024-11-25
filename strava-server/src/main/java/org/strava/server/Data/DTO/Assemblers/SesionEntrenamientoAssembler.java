package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.Dominio.SesionEntrenamientoDO;
import org.strava.server.Data.DTO.SesionEntrenamientoDTO;

import java.time.Duration;
import java.util.Date;

public class SesionEntrenamientoAssembler {
    public static SesionEntrenamientoDTO doToDto(SesionEntrenamientoDO sesionEntrenamientoDo) {
        SesionEntrenamientoDTO sesionEntrenamientoDto= new SesionEntrenamientoDTO();

        sesionEntrenamientoDto.setId(sesionEntrenamientoDo.getId());
        sesionEntrenamientoDto.setTitulo(sesionEntrenamientoDo.getTitulo());
        sesionEntrenamientoDto.setDistanciaKm(sesionEntrenamientoDo.getDistanciaKm());
        sesionEntrenamientoDto.setFechaInicio(sesionEntrenamientoDo.getFechaInicio());
        sesionEntrenamientoDto.setHoraInicio(sesionEntrenamientoDo.getHoraInicio());
        sesionEntrenamientoDto.setDuracion(sesionEntrenamientoDo.getDuracion());

        return sesionEntrenamientoDto;
    }
    public static SesionEntrenamientoDO dtoToDo(SesionEntrenamientoDTO sesionEntrenamientoDto) {
        SesionEntrenamientoDO sesionEntrenamientoDo = new SesionEntrenamientoDO();

        sesionEntrenamientoDo.setId(sesionEntrenamientoDto.getId());
        sesionEntrenamientoDo.setTitulo(sesionEntrenamientoDto.getTitulo());
        sesionEntrenamientoDo.setDistanciaKm(sesionEntrenamientoDto.getDistanciaKm());
        sesionEntrenamientoDo.setFechaInicio(sesionEntrenamientoDto.getFechaInicio());
        sesionEntrenamientoDo.setHoraInicio(sesionEntrenamientoDto.getHoraInicio());
        sesionEntrenamientoDo.setDuracion(sesionEntrenamientoDto.getDuracion());

        return sesionEntrenamientoDo;
    }
}
