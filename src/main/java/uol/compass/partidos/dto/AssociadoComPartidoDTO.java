package uol.compass.partidos.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AssociadoComPartidoDTO extends AssociadoDTO {
    private PartidoDTO partido;
}
