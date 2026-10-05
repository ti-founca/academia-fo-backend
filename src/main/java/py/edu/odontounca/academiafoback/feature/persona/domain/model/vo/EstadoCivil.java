package py.edu.odontounca.academiafoback.feature.persona.domain.model.vo;

public enum EstadoCivil {
    SOLTERO("S"),
    CASADO("C"),
    DIVORCIADO("D"),
    VIUDO("V");

    private final String code;

    EstadoCivil(String code) {
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public static EstadoCivil fromCode(String code){
        for(EstadoCivil estadoCivil : values()){
            if(estadoCivil.code.equals(code)) return estadoCivil;
        }
        throw new IllegalArgumentException("Estado civil desconocido: «" + code + "»");
    }
}
