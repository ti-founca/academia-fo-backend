package py.edu.odontounca.academiafoback.feature.autenticacion.application.exception;

import py.edu.odontounca.academiafoback.shared.exception.ApplicationError;

public class TokenInvalidoError extends ApplicationError {
    public TokenInvalidoError(){
        super("TOKEN_INVALIDO", "Token inválido o expirado");
    }
}
