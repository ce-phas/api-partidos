package uol.compass.partidos.dto;

import lombok.Data;
import uol.compass.partidos.entity.enums.Ideologia;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PartidoDTO {

    private String nome;
    private String sigla;
    private Ideologia ideologia;
    private LocalDate dataFundacao;
    private Set<AssociadoDTO> associados;
}
