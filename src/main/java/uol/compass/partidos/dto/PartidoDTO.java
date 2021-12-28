package uol.compass.partidos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;
}
