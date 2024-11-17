package org.strava.server.Data.DTO.Assemblers;

import org.strava.server.Data.Dominio.UsuarioDO;
import org.strava.server.Data.DTO.UsuarioDTO;

public class UsuarioAssembler {
    public static UsuarioDTO doToDto(UsuarioDO usuarioDo) {
        UsuarioDTO usuarioDto = new UsuarioDTO();

        usuarioDto.setId(usuarioDo.getId());
        usuarioDto.setEmail(usuarioDo.getEmail());
        usuarioDto.setNombre(usuarioDo.getNombre());
        usuarioDto.setFechaNacimiento(usuarioDo.getFechaNacimiento());

        // TODO: Hacer estas
        // usuarioDto.setAlturaCm(usuarioDo.getAlturaCm());
        // usuarioDto.setMetodoRegistro(usuarioDo.getMetodoRegistro());
        // usuarioDto.setPesoKg(usuarioDo.getPesoKg());
        // usuarioDto.setFrecuenciaCardiacaReposo(usuarioDo.getFrecuenciaCardiacaReposo());
        // usuarioDto.setFrecuenciaCardiacaMax(usuarioDo.getFrecuenciaCardiacaMax());

        return usuarioDto;
    }

    public static UsuarioDO dtoToDo(UsuarioDTO usuarioDto) {
        UsuarioDO usuarioDo = new UsuarioDO();

        // Si el usuario se esta registrando, su ID sera nula, pues
        // aun no esta registrado.
        if(usuarioDto.getId() != null)
            usuarioDo.setId(usuarioDto.getId());

        usuarioDo.setNombre(usuarioDto.getNombre());
        usuarioDo.setEmail(usuarioDto.getEmail());
        usuarioDo.setFechaNacimiento(usuarioDto.getFechaNacimiento());

        // TODO: Hacer estas
        //usuarioDo.setAlturaCm(usuarioDto.getAlturaCm());
        //usuarioDo.setMetodoRegistro(usuarioDto.getMetodoRegistro());
        // usuarioDo.setPesoKg(usuarioDto.getPesoKg());
        // usuarioDo.setFrecuenciaCardiacaReposo(usuarioDto.getFrecuenciaCardiacaReposo());
        // usuarioDo.setFrecuenciaCardiacaMax(usuarioDto.getFrecuenciaCardiacaMax());

        return usuarioDo;
    }
}
