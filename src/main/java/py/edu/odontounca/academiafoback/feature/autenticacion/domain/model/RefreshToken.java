package py.edu.odontounca.academiafoback.feature.autenticacion.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

import java.time.Instant;
import java.util.UUID;

public class RefreshToken {
    private Integer id;
    private UUID jti;
    private Integer idUsuario;
    private Instant emitidoEn;
    private Instant expiraEn;
    private Instant revocadoEn;

    public RefreshToken(Integer id, UUID jti, Integer idUsuario, Instant emitidoEn, Instant expiraEn, Instant revocadoEn) {
        Assert.notNull(jti, "RefreshToken: «jti» no puede ser nulo");
        Assert.notNull(idUsuario, "RefreshToken: «idUsuario» no puede ser nulo");
        Assert.notNull(emitidoEn, "RefreshToken: «emitidoEn» no puede ser nulo");
        Assert.notNull(expiraEn, "RefreshToken: «expiraEn» no puede ser nulo");
        if(!expiraEn.isAfter(emitidoEn)) throw new IllegalArgumentException("RefreshToken: «expiraEn» debe ser posterior a «emitidoEn»");
        this.id = id;
        this.jti = jti;
        this.idUsuario = idUsuario;
        this.emitidoEn = emitidoEn;
        this.expiraEn = expiraEn;
        this.revocadoEn = revocadoEn;
    }

    public static RefreshToken reconstruir(Integer id, UUID jti, Integer idUsuario, Instant emitidoEn, Instant expiraEn, Instant revocadoEn){
        Assert.notNull(id, "RefreshToken: «id» no puede ser nulo");
        return new RefreshToken(id, jti, idUsuario, emitidoEn, expiraEn, revocadoEn);
    }

    public static RefreshToken crear(UUID jti, Integer idUsuario, Instant emitidoEn, Instant expiraEn){
        return new RefreshToken(null, jti, idUsuario, emitidoEn, expiraEn, null);
    }

    public boolean estaVigente(Instant ahora){
        return this.revocadoEn == null && ahora.isBefore(this.expiraEn);
    }

    public void revocar(Instant ahora){
        if(this.revocadoEn == null) this.revocadoEn = ahora;
    }

    public Integer getId() { return id; }
    public UUID getJti() { return jti; }
    public Integer getIdUsuario() { return idUsuario; }
    public Instant getEmitidoEn() { return emitidoEn; }
    public Instant getExpiraEn() { return expiraEn; }
    public Instant getRevocadoEn() { return revocadoEn; }
}
