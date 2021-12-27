package uol.compass.partidos.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Ideologia {
    CENTRO("Centro"),
    DIREITA("Direita"),
    ESQUERDA("Esquerda");

    private String valor;

    Ideologia(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Ideologia forValues(@JsonProperty("ideologia") String valor) {
        for (Ideologia ideologia : Ideologia.values()) {
            if (ideologia.valor.equalsIgnoreCase(valor)) {
                return ideologia;
            }
        }

        return null;
    }
}
