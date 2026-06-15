package py.edu.odontounca.academiafoback.shared.validation;

import java.time.LocalDateTime;

public class Assert {
    public static void maxLength(String valor, int max, String mensaje){
        if(valor != null && valor.length() > max) throw new IllegalArgumentException(mensaje);
    }
    public static void notNull(Object valor, String mensaje){
        if(valor == null) throw new IllegalArgumentException(mensaje);
    }
    public static void isBefore(LocalDateTime valor, LocalDateTime referencia, String mensaje) {
        if (valor != null && !valor.isBefore(referencia)) throw new IllegalArgumentException(mensaje);
    }
    public static void isAfter(LocalDateTime valor, LocalDateTime referencia, String mensaje) {
        if (valor != null && !valor.isAfter(referencia)) throw new IllegalArgumentException(mensaje);
    }
    public static void isBetweenInclusive(Integer valor, int min, int max, String mensaje){
        if(valor != null && valor < min || valor > max) throw new IllegalArgumentException(mensaje);
    }

}
