package py.edu.odontounca.academiafoback.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.exception.CredencialesInvalidasError;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.exception.TokenInvalidoError;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundError.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundError ex){
        return new ErrorResponse(ex.getMessage(), Map.copyOf(ex.getDetails()));
    }

    @ExceptionHandler({CredencialesInvalidasError.class, TokenInvalidoError.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleUnauthorizedException(ApplicationError ex){
        return new ErrorResponse(ex.getMessage(), Map.of("code", ex.getErrorCode()));
    }

}
