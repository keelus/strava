package org.strava.cliente.gui.FormularioExterno.LoginParaRegistro;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;
import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;
import org.strava.server.Data.DTO.DatosRegistroDTO;

import java.awt.*;

public class FormularioExternoLoginParaRegistroMeta extends FormularioExternoLoginParaRegistro {
    public FormularioExternoLoginParaRegistroMeta(CallbackFormularioExterno callback, DatosRegistroDTO datosRegistroDto) {
        super(
                callback, FormularioExternoServicio.META,
                datosRegistroDto,
                "/meta.png", datosRegistroDto.getEmail(), new Color(0, 127, 248));
    }
}
