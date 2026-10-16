package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.adapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.contract.CifradorClave;

@Component
public class BCryptCifradorClave implements CifradorClave {
    // Mismo costo que la migración V4 (gen_salt('bf', 10))
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    private final String hashFicticio = encoder.encode("clave-ficticia-para-igualar-tiempos");

    @Override
    public boolean coincide(String clavePlana, String claveCifrada) {
        if(clavePlana == null) return false;
        if(claveCifrada == null || claveCifrada.isEmpty()){
            this.encoder.matches(clavePlana, this.hashFicticio);
            return false;
        }
        return this.encoder.matches(clavePlana, claveCifrada);
    }
}
