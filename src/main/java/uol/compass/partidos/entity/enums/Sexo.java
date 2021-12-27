package uol.compass.partidos.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {
    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private String valor;

    Sexo(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Sexo forValues(@JsonProperty("sexo") String valor) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.valor.equalsIgnoreCase(valor)) {
                return sexo;
            }
        }

        return null;
    }
}
