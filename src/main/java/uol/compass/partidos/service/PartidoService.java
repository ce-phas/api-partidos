package uol.compass.partidos.service;

import uol.compass.partidos.dto.AssociadoDTO;
import uol.compass.partidos.dto.PartidoDTO;
import uol.compass.partidos.dto.form.PartidoFormDTO;

import java.util.List;

public interface PartidoService {

    PartidoDTO save(PartidoFormDTO body);

    List<PartidoDTO> getPartidos();

    PartidoDTO searchPartido(Long id);

    PartidoDTO updatePartido(PartidoFormDTO body);

    PartidoDTO deletePartido(Long id);

    List<AssociadoDTO> getAssociadosPartido(Long id);
}
