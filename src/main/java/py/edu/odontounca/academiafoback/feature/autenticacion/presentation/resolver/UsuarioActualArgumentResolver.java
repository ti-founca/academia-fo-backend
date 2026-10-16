package py.edu.odontounca.academiafoback.feature.autenticacion.presentation.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.UsuarioAutenticadoInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.exception.NoAutenticadoError;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.annotation.UsuarioActual;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.interceptor.AutenticacionInterceptor;

@Component
public class UsuarioActualArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UsuarioActual.class)
                && parameter.getParameterType().equals(UsuarioAutenticadoInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Object usuario = webRequest.getAttribute(AutenticacionInterceptor.ATRIBUTO_USUARIO, RequestAttributes.SCOPE_REQUEST);
        // Solo ocurre si se usa @UsuarioActual en una ruta excluida del interceptor
        if(usuario == null) throw new NoAutenticadoError();
        return usuario;
    }
}
