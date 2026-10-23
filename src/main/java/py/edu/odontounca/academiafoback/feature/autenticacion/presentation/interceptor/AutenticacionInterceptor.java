package py.edu.odontounca.academiafoback.feature.autenticacion.presentation.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.exception.NoAutenticadoError;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.usecase.ValidarAccessTokenUseCase;

/** Exige un access token válido en «Authorization: Bearer ...» y deja el usuario como atributo del request. */
@Component
@RequiredArgsConstructor
public class AutenticacionInterceptor implements HandlerInterceptor {
    public static final String ATRIBUTO_USUARIO = "usuarioAutenticado";
    private static final String PREFIJO_BEARER = "Bearer ";

    private final ValidarAccessTokenUseCase validarAccessTokenUseCase;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Los preflight de CORS no llevan Authorization; se dejan pasar para no romper CORS
        if(CorsUtils.isPreFlightRequest(request)) return true;

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null || !header.regionMatches(true, 0, PREFIJO_BEARER, 0, PREFIJO_BEARER.length())){
            throw new NoAutenticadoError();
        }
        String token = header.substring(PREFIJO_BEARER.length()).trim();

        ValidarAccessTokenUseCase.Result result = this.validarAccessTokenUseCase.execute(
                new ValidarAccessTokenUseCase.Query(token)
        );
        request.setAttribute(ATRIBUTO_USUARIO, result.usuario());
        return true;
    }
}
