package py.edu.odontounca.academiafoback.feature.autenticacion.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.UsuarioAutenticadoInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.usecase.CerrarSesionUseCase;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.usecase.IniciarSesionUseCase;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.usecase.RefrescarTokenUseCase;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.annotation.UsuarioActual;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.dto.LoginRequestDTO;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.dto.RefreshRequestDTO;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.dto.TokensDTO;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.dto.UsuarioAutenticadoDTO;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.mapper.AutenticacionResponseMapper;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AutenticacionResponseMapper mapper;
    private final IniciarSesionUseCase iniciarSesionUseCase;
    private final RefrescarTokenUseCase refrescarTokenUseCase;
    private final CerrarSesionUseCase cerrarSesionUseCase;

    @PostMapping("/login")
    public TokensDTO login(@RequestBody LoginRequestDTO request){
        IniciarSesionUseCase.Result result = this.iniciarSesionUseCase.execute(
                new IniciarSesionUseCase.Query(request.correo(), request.clave())
        );
        return this.mapper.toResponse(result.tokens());
    }

    @PostMapping("/refresh")
    public TokensDTO refresh(@RequestBody RefreshRequestDTO request){
        RefrescarTokenUseCase.Result result = this.refrescarTokenUseCase.execute(
                new RefrescarTokenUseCase.Query(request.refreshToken())
        );
        return this.mapper.toResponse(result.tokens());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody RefreshRequestDTO request){
        this.cerrarSesionUseCase.execute(new CerrarSesionUseCase.Query(request.refreshToken()));
    }

    @GetMapping("/me")
    public UsuarioAutenticadoDTO me(@UsuarioActual UsuarioAutenticadoInfo usuario){
        return this.mapper.toResponse(usuario);
    }
}
