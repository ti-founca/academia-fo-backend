package py.edu.odontounca.academiafoback.shared.validation;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public static void isBefore(LocalDate valor, LocalDate referencia, String mensaje) {
        if (valor != null && !valor.isBefore(referencia)) throw new IllegalArgumentException(mensaje);
    }
    public static void isAfter(LocalDate valor, LocalDate referencia, String mensaje) {
        if (valor != null && !valor.isAfter(referencia)) throw new IllegalArgumentException(mensaje);
    }
    public static void isBetweenInclusive(Integer valor, int min, int max, String mensaje){
        if(valor != null && valor < min || valor > max) throw new IllegalArgumentException(mensaje);
    }
    public static void isNumeric(String valor, String mensaje){
        try{
            new BigDecimal(valor);
        }catch (NumberFormatException ex){
            throw new IllegalArgumentException(mensaje);
        }
    }
    public static void isBetweenInclusive(BigDecimal valor, BigDecimal min, BigDecimal max, String mensaje){
        if(valor != null && valor.compareTo(min) < 0  || valor.compareTo(max) > 0) throw new IllegalArgumentException(mensaje);
    }
    public static void isBetweenInclusive(BigDecimal valor, String min, String max, String mensaje){
        Assert.isNumeric(min, "El valor mínimo minimo debe ser un número");
        Assert.isNumeric(max, "El valor máximo del rango debe ser un número");
        if(valor != null && valor.compareTo(new BigDecimal(min)) < 0  || valor.compareTo(new BigDecimal(max)) > 0) throw new IllegalArgumentException(mensaje);
    }
}
