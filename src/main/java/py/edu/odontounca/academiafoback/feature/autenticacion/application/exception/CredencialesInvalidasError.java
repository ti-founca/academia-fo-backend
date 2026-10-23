package py.edu.odontounca.academiafoback.feature.autenticacion.application.exception;

import py.edu.odontounca.academiafoback.shared.exception.ApplicationError;

public class CredencialesInvalidasError extends ApplicationError {
    public CredencialesInvalidasError(){
        super("CREDENCIALES_INVALIDAS", "Credenciales inválidas");
    }
}
