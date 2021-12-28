package uol.compass.partidos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.partidos.entity.enums.Cargo;
import uol.compass.partidos.entity.enums.Sexo;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AssociadoDTO implements Serializable {

    private Long id;

    private String nome;

    private Cargo cargo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNasc;

    private Sexo sexo;
}
