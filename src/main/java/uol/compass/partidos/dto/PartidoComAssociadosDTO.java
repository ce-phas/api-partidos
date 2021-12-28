package uol.compass.partidos.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PartidoComAssociadosDTO extends PartidoDTO {

    private List<AssociadoDTO> associados;
}
