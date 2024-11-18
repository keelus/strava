package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.DTO.UsuarioNuevoDTO;
import org.strava.server.Data.Dominio.UsuarioNuevoDO;

import java.util.Date;

public class UsuarioNuevoAssembler {
    public static UsuarioNuevoDTO doToDto(UsuarioNuevoDO usuarioNuevoDo) {
        UsuarioNuevoDTO usuarioNuevoDto = new UsuarioNuevoDTO();

        usuarioNuevoDto.setEmail(usuarioNuevoDo.getEmail());
        usuarioNuevoDto.setNombre(usuarioNuevoDo.getNombre());
        usuarioNuevoDto.setMetodoRegistro(usuarioNuevoDo.getMetodoRegistro());
        usuarioNuevoDto.setFechaNacimiento(usuarioNuevoDo.getFechaNacimiento());
        usuarioNuevoDto.setPesoKg(usuarioNuevoDo.getPesoKg());
        usuarioNuevoDto.setAlturaCm(usuarioNuevoDo.getAlturaCm());
        usuarioNuevoDto.setFrecuenciaCardiacaMax(usuarioNuevoDo.getFrecuenciaCardiacaMax());
        usuarioNuevoDto.setFrecuenciaCardiacaReposo(usuarioNuevoDo.getFrecuenciaCardiacaReposo());

        return usuarioNuevoDto;
    }

    public static UsuarioNuevoDO dtoToDo(UsuarioNuevoDTO usuarioNuevoDto) {
        UsuarioNuevoDO usuarioNuevoDo = new UsuarioNuevoDO();

        usuarioNuevoDo.setEmail(usuarioNuevoDto.getEmail());
        usuarioNuevoDo.setNombre(usuarioNuevoDto.getNombre());
        usuarioNuevoDo.setMetodoRegistro(usuarioNuevoDto.getMetodoRegistro());
        usuarioNuevoDo.setFechaNacimiento(usuarioNuevoDto.getFechaNacimiento());
        usuarioNuevoDo.setPesoKg(usuarioNuevoDto.getPesoKg());
        usuarioNuevoDo.setAlturaCm(usuarioNuevoDto.getAlturaCm());
        usuarioNuevoDo.setFrecuenciaCardiacaMax(usuarioNuevoDto.getFrecuenciaCardiacaMax());
        usuarioNuevoDo.setFrecuenciaCardiacaReposo(usuarioNuevoDto.getFrecuenciaCardiacaReposo());

        return usuarioNuevoDo;
    }
}
