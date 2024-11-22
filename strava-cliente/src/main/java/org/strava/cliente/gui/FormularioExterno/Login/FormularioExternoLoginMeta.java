package org.strava.cliente.gui.FormularioExterno.Login;

import org.strava.cliente.gui.FormularioExterno.CallbackFormularioExterno;
import org.strava.cliente.gui.FormularioExterno.LoginParaRegistro.DatosRegistro;
import org.strava.cliente.gui.FormularioExterno.LoginParaRegistro.FormularioExternoLoginParaRegistro;
import org.strava.cliente.gui.FormularioExterno.LoginParaRegistro.FormularioExternoLoginParaRegistroAccionDeseada;

import java.awt.*;

public class FormularioExternoLoginMeta extends FormularioExternoLogin {
    public FormularioExternoLoginMeta(CallbackFormularioExterno callback) {
        super(
                callback, FormularioExternoLoginAccionDeseada.CON_META,
                "/meta.png", new Color(0, 128, 250));
    }
}
