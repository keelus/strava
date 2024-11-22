package org.strava.cliente.gui.FormularioExterno.LoginParaRegistro;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;
import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;
import org.strava.server.Data.DTO.DatosRegistroDTO;

import java.awt.*;

public class FormularioExternoLoginParaRegistroGoogle extends FormularioExternoLoginParaRegistro {
    public FormularioExternoLoginParaRegistroGoogle(CallbackFormularioExterno callback, DatosRegistroDTO datosRegistroDto) {
        super(
                callback, FormularioExternoServicio.GOOGLE,
                datosRegistroDto,
                "/google.png", datosRegistroDto.getEmail(), new Color(11, 87, 208));
    }
}
