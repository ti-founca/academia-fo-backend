package py.edu.odontounca.academiafoback.feature.persona.domain.model.vo;

public enum Sexo {
    MASCULINO("M"),
    FEMENINO("F");

    private final String code;

    Sexo(String code) {
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public static Sexo fromCode(String code){
        for(Sexo sexo : values()){
            if(sexo.code.equals(code)) return sexo;
        }
        throw new IllegalArgumentException("Sexo desconocido: «" + code + "»");
    }
}
