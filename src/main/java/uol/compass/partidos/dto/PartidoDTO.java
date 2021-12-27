package uol.compass.partidos.dto;

import lombok.Data;
import uol.compass.partidos.entity.enums.Ideologia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class PartidoDTO implements Serializable {
    private Long id;
    private String nome;
    private String sigla;
    private Ideologia ideologia;
    private LocalDate dataFundacao;
}
