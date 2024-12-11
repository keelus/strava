package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.DTO.SesionEntrenamientoNuevoDTO;
import org.strava.server.Data.Dominio.SesionEntrenamientoNuevoDO;

public class SesionEntrenamientoNuevoAssembler {
    public static SesionEntrenamientoNuevoDTO doToDto(SesionEntrenamientoNuevoDO sesionEntrenamientoNuevoDo) {
        SesionEntrenamientoNuevoDTO sesionEntrenamientoNuevoDto = new SesionEntrenamientoNuevoDTO();

        sesionEntrenamientoNuevoDto.setTitulo(sesionEntrenamientoNuevoDo.getTitulo());
        sesionEntrenamientoNuevoDto.setDistanciaKm(sesionEntrenamientoNuevoDo.getDistanciaKm());
        sesionEntrenamientoNuevoDto.setFechaInicio(sesionEntrenamientoNuevoDo.getFechaInicio());
        sesionEntrenamientoNuevoDto.setHoraInicio(sesionEntrenamientoNuevoDo.getHoraInicio());
        sesionEntrenamientoNuevoDto.setDuracion(sesionEntrenamientoNuevoDo.getDuracion());
        sesionEntrenamientoNuevoDto.setDeporte(sesionEntrenamientoNuevoDo.getDeporte());

        return sesionEntrenamientoNuevoDto;
    }
    public static SesionEntrenamientoNuevoDO dtoToDo(SesionEntrenamientoNuevoDTO sesionEntrenamientoNuevoDto) {
        SesionEntrenamientoNuevoDO sesionEntrenamientoNuevoDo = new SesionEntrenamientoNuevoDO();

        sesionEntrenamientoNuevoDo.setTitulo(sesionEntrenamientoNuevoDto.getTitulo());
        sesionEntrenamientoNuevoDo.setDistanciaKm(sesionEntrenamientoNuevoDto.getDistanciaKm());
        sesionEntrenamientoNuevoDo.setFechaInicio(sesionEntrenamientoNuevoDto.getFechaInicio());
        sesionEntrenamientoNuevoDo.setHoraInicio(sesionEntrenamientoNuevoDto.getHoraInicio());
        sesionEntrenamientoNuevoDo.setDuracion(sesionEntrenamientoNuevoDto.getDuracion());
        sesionEntrenamientoNuevoDo.setDeporte(sesionEntrenamientoNuevoDto.getDeporte());

        return sesionEntrenamientoNuevoDo;
    }
}
