package uol.compass.partidos.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.partidos.entity.enums.Ideologia;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PartidoFormDTO {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String nome;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String sigla;

    @NotNull(message = "a ideologia deve ser um dos seguintes valores: 'Centro', 'Direita', 'Esquerda'")
    private Ideologia ideologia;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "a data deve obedecer ao seguinte formato: 'dd/MM/yyyy'")
    private LocalDate dataFundacao;
}
