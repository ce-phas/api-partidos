package uol.compass.partidos.dto.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FiliacaoFormDTO {

    @NotNull
    private Long idAssociado;

    @NotNull
    private Long idPartido;
}
