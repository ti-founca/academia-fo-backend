package py.edu.odontounca.academiafoback.feature.autenticacion.presentation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Inyecta en un parámetro de controller el {@code UsuarioAutenticadoInfo} validado por el interceptor. */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UsuarioActual {
}
