package org.strava.server.AutenticacionGateway;

import org.strava.server.Data.Enums.MetodoRegistro;

public class AutenticacionGatewayFactory {
    private static AutenticacionGatewayFactory instance;

    public static IAutenticacionGateway crearAutenticationGateway(MetodoRegistro metodoRegistro) {
        return switch (metodoRegistro) {
            case Meta -> new MetaGateway();
            case Google -> new GoogleGateway();
        };
    }

}
