package org.strava.cliente.gui.FormularioExterno.Login;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;

import java.awt.*;

public class FormularioExternoLoginGoogle extends FormularioExternoLogin {
    public FormularioExternoLoginGoogle(CallbackFormularioExterno callback) {
        super(
                callback, FormularioExternoLoginAccionDeseada.CON_GOOGLE,
                "/google.png", new Color(11, 87, 208));
    }
}
