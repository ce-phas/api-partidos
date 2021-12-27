package uol.compass.partidos.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Cargo {
    VEREADOR("Vereador"),
    PREFEITO("Prefeito"),
    DEPUTADO_ESTADUAL("Deputado Estadual"),
    DEPUTADO_FEDERAL("Deputado Federal"),
    SENADOR("Senador"),
    GOVERNADOR("Governador"),
    PRESIDENTE("Presidente"),
    NENHUM("Nenhum");

    private final String valor;

    Cargo(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Cargo forValues(@JsonProperty("cargo") String valor) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.valor.equalsIgnoreCase(valor)) {
                return cargo;
            }
        }

        return null;
    }
}
