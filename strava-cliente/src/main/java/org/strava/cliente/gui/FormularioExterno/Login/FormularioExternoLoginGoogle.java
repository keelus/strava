package org.strava.cliente.gui.FormularioExterno.Login;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;
import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;

import java.awt.*;

public class FormularioExternoLoginGoogle extends FormularioExternoLogin {
    public FormularioExternoLoginGoogle(CallbackFormularioExterno callback) {
        super(
                callback, FormularioExternoServicio.Google,
                "/google.png", new Color(11, 87, 208));
    }
}
