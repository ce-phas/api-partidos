package uol.compass.partidos.service;

import uol.compass.partidos.dto.AssociadoDTO;
import uol.compass.partidos.dto.form.AssociadoFormDTO;

import java.util.List;

public interface AssociadoService {

    AssociadoDTO save(AssociadoFormDTO body);

    List<AssociadoDTO> getAssociados();

    AssociadoDTO searchAssociado(Long id);

    AssociadoDTO updateAssociado(AssociadoFormDTO body);

    AssociadoDTO deleteAssociado(Long id);

    AssociadoDTO addFiliacao(Long id, Long idPartido);

    AssociadoDTO removeFiliacao(Long id, Long idPartido);
}
