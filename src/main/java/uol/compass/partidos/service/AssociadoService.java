package uol.compass.partidos.service;

import uol.compass.partidos.dto.AssociadoComPartidoDTO;
import uol.compass.partidos.dto.AssociadoDTO;
import uol.compass.partidos.dto.form.AssociadoFormDTO;
import uol.compass.partidos.dto.form.FiliacaoFormDTO;
import uol.compass.partidos.entity.enums.Cargo;

import java.util.List;

public interface AssociadoService {

    AssociadoDTO save(AssociadoFormDTO body);

    List<AssociadoDTO> getAssociados(String cargo, Boolean sort);

    AssociadoDTO searchAssociado(Long id);

    AssociadoDTO updateAssociado(Long id, AssociadoFormDTO body);

    AssociadoDTO deleteAssociado(Long id);

    AssociadoComPartidoDTO addFiliacao(FiliacaoFormDTO body);

    AssociadoComPartidoDTO removeFiliacao(Long id);
}
