package py.edu.odontounca.academiafoback.feature.curso.domain.model.vo;

public enum OrdenSemestre {
    PRIMER_SEMESTRE(1),
    SEGUNDO_SEMESTRE(2);

    private final int code;

    OrdenSemestre(int code) { this.code = code; }

    public int getCode(){ return this.code; }

    public static OrdenSemestre fromCode(int code){
        for(OrdenSemestre rango : values()){
            if(rango.code == code) return rango;
        }
        throw new IllegalArgumentException("Orden de semestre desconocido, debe ser 1 o 2: «" + code + "»");
    }
}
