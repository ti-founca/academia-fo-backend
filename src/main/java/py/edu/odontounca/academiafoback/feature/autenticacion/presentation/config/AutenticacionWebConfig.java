package py.edu.odontounca.academiafoback.feature.autenticacion.presentation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.interceptor.AutenticacionInterceptor;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.resolver.UsuarioActualArgumentResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AutenticacionWebConfig implements WebMvcConfigurer {
    private final AutenticacionInterceptor autenticacionInterceptor;
    private final UsuarioActualArgumentResolver usuarioActualArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.autenticacionInterceptor)
                .addPathPatterns("/api/**")
                // logout es público: ya exige el refresh token y debe funcionar con el access token expirado
                .excludePathPatterns("/api/auth/login", "/api/auth/refresh", "/api/auth/logout");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(this.usuarioActualArgumentResolver);
    }
}
