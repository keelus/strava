package org.strava.cliente.gui.FormularioExterno.LoginParaRegistro;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;

import java.awt.*;

public class FormularioExternoLoginParaRegistroGoogle extends FormularioExternoLoginParaRegistro {
    public FormularioExternoLoginParaRegistroGoogle(CallbackFormularioExterno callback, DatosRegistro datosRegistro) {
        super(
                callback, FormularioExternoLoginParaRegistroAccionDeseada.CON_GOOGLE,
                datosRegistro,
                "/google.png", datosRegistro.getEmail(), new Color(11, 87, 208));
    }
}
