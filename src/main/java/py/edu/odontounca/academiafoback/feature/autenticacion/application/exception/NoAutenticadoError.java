package py.edu.odontounca.academiafoback.feature.autenticacion.application.exception;

import py.edu.odontounca.academiafoback.shared.exception.ApplicationError;

public class NoAutenticadoError extends ApplicationError {
    public NoAutenticadoError(){
        super("NO_AUTENTICADO", "Se requiere autenticación");
    }
}
