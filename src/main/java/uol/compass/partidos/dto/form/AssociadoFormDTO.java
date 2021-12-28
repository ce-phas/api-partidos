package uol.compass.partidos.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.partidos.entity.enums.Cargo;
import uol.compass.partidos.entity.enums.Sexo;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AssociadoFormDTO {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String nome;

    @NotNull(message = "este campo não pode estar vazio e deve ser um dos seguintes valores: 'Vereador', 'Prefeito', "
            + "'Deputado Estadual, 'Deputado Federal', 'Senador', 'Governador', 'Presidente', 'Nenhum'")
    private Cargo cargo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "este campo não pode estar vazio e deve obedecer ao seguinte formato: 'dd/MM/yyyy'")
    private LocalDate dataNasc;


    @NotNull(message = "este campo não pode estar vazio e deve ser um dos seguintes valores: 'Feminino', 'Masculino'")
    private Sexo sexo;
}
