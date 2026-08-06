package py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.vo;

public enum NumeroSemestre {
    ANHO(0),
    PRIMER_SEMESTRE(1),
    SEGUNDO_SEMESTRE(2);

    private final int code;

    NumeroSemestre(int code) {
        this.code = code;
    }

    public static NumeroSemestre fromCode(int code){
        for(NumeroSemestre rango : values()){
            if(rango.code == code) return rango;
        }
        throw new IllegalArgumentException("Rango de Periodo Lectivo desconocido: «" + code + "»");
    }
}
