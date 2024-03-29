package uol.compass.partidos.service;

import uol.compass.partidos.dto.PartidoComAssociadosDTO;
import uol.compass.partidos.dto.PartidoDTO;
import uol.compass.partidos.dto.form.PartidoFormDTO;

import java.util.List;

public interface PartidoService {

    PartidoDTO save(PartidoFormDTO body);

    List<PartidoDTO> getPartidos(String ideologia);

    PartidoDTO searchPartido(Long id);

    PartidoDTO updatePartido(Long id, PartidoFormDTO body);

    PartidoDTO deletePartido(Long id);

    PartidoComAssociadosDTO getAssociadosPartido(Long id);
}
