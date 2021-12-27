package uol.compass.partidos.dto;

import uol.compass.partidos.entity.enums.Cargo;
import uol.compass.partidos.entity.enums.Sexo;

import java.time.LocalDate;

public class AssociadoDTO {

    private String nome;
    private Cargo cargo;
    private LocalDate dataNasc;
    private Sexo sexo;
    private PartidoDTO partido;
}
