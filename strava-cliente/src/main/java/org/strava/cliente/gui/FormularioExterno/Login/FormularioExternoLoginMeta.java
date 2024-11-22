package org.strava.cliente.gui.FormularioExterno.Login;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;
import org.strava.cliente.gui.FormularioExterno.FormularioExternoServicio;

import java.awt.*;

public class FormularioExternoLoginMeta extends FormularioExternoLogin {
    public FormularioExternoLoginMeta(CallbackFormularioExterno callback) {
        super(
                callback, FormularioExternoServicio.META,
                "/meta.png", new Color(0, 128, 250));
    }
}
