package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.DTO.DatosRegistroDTO;
import org.strava.server.Data.Dominio.DatosRegistroDO;

public class DatosRegistroAssembler {
    public static DatosRegistroDTO doToDto(DatosRegistroDO datosRegistroDo) {
        DatosRegistroDTO datosRegistroDto = new DatosRegistroDTO();

        datosRegistroDto.setEmail(datosRegistroDo.getEmail());
        datosRegistroDto.setContrasenya(datosRegistroDo.getContrasenya());
        datosRegistroDto.setNombre(datosRegistroDo.getNombre());
        datosRegistroDto.setMetodoRegistro(datosRegistroDo.getMetodoRegistro());
        datosRegistroDto.setFechaNacimiento(datosRegistroDo.getFechaNacimiento());
        datosRegistroDto.setPesoKg(datosRegistroDo.getPesoKg());
        datosRegistroDto.setAlturaCm(datosRegistroDo.getAlturaCm());
        datosRegistroDto.setFrecuenciaCardiacaMax(datosRegistroDo.getFrecuenciaCardiacaMax());
        datosRegistroDto.setFrecuenciaCardiacaReposo(datosRegistroDo.getFrecuenciaCardiacaReposo());

        return datosRegistroDto;
    }

    public static DatosRegistroDO dtoToDo(DatosRegistroDTO datosRegistroDto) {
        DatosRegistroDO datosRegistroDo = new DatosRegistroDO();

        datosRegistroDo.setEmail(datosRegistroDto.getEmail());
        datosRegistroDo.setContrasenya(datosRegistroDto.getContrasenya());
        datosRegistroDo.setNombre(datosRegistroDto.getNombre());
        datosRegistroDo.setMetodoRegistro(datosRegistroDto.getMetodoRegistro());
        datosRegistroDo.setFechaNacimiento(datosRegistroDto.getFechaNacimiento());
        datosRegistroDo.setPesoKg(datosRegistroDto.getPesoKg());
        datosRegistroDo.setAlturaCm(datosRegistroDto.getAlturaCm());
        datosRegistroDo.setFrecuenciaCardiacaMax(datosRegistroDto.getFrecuenciaCardiacaMax());
        datosRegistroDo.setFrecuenciaCardiacaReposo(datosRegistroDto.getFrecuenciaCardiacaReposo());

        return datosRegistroDo;
    }
}
