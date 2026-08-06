package py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.vo;

public enum TipoPeriodoLectivo {
    ANHO("A"),
    SEMESTRE("S");

    private final String code;

    private TipoPeriodoLectivo(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public static TipoPeriodoLectivo fromCode(String code){
        for(TipoPeriodoLectivo tipo : values()){
            if(tipo.code.equals(code)) return tipo;
        }
        throw new IllegalArgumentException("Tipo de Periodo Lectivo desconocido: «" + code + "»");
    }

}
