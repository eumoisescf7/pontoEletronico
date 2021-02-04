package br.com.ilia.pontoeletronico.entity.enums;

public enum StatusPontoEnum {

    SAIDA_1(1, "SAIDA_1"),
    ENTRADA_2(2, "ENTRADA_2"),
    SAIDA_2(3, "SAIDA_2"),
    NAO_ENCONTRADO(4, "NÃO ENCONTRADO");

    private Integer code;
    private String value;

    StatusPontoEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static StatusPontoEnum detect(Integer code) {
        for (StatusPontoEnum status : StatusPontoEnum.values()) {
            if(status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
