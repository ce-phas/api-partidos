package uol.compass.partidos.dto.form;

import lombok.Data;
import uol.compass.partidos.entity.enums.Cargo;
import uol.compass.partidos.entity.enums.Sexo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class AssociadoFormDTO {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String nome;

    @NotNull(message = "o cargo deve ser um dos seguintes valores: 'Vereador', 'Prefeito', 'Deputado Estadual, "
            + "'Deputado Federal', 'Senador', 'Governador', 'Presidente', 'Nenhum'")
    @NotEmpty(message = "para um associado sem cargo, deve-se informar o valor 'Nenhum' neste campo")
    private Cargo cargo;

    @NotNull(message = "a data deve obedecer ao seguinte formato: 'dd/MM/yyyy'")
    @NotEmpty
    private LocalDate dataNasc;


    @NotNull(message = "o sexo deve ser um dos seguintes valores: 'Feminino', 'Masculino'")
    @NotEmpty
    private Sexo sexo;
}
