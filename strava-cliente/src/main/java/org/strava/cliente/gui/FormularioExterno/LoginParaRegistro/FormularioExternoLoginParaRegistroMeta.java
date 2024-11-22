package org.strava.cliente.gui.FormularioExterno.LoginParaRegistro;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;

import java.awt.*;

public class FormularioExternoLoginParaRegistroMeta extends FormularioExternoLoginParaRegistro {
    public FormularioExternoLoginParaRegistroMeta(CallbackFormularioExterno callback, DatosRegistro datosRegistro) {
        super(
                callback, FormularioExternoLoginParaRegistroAccionDeseada.CON_META,
                datosRegistro,
                "/meta.png", datosRegistro.getEmail(), new Color(0, 127, 248));
    }
}
