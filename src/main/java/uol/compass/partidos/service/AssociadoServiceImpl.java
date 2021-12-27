package uol.compass.partidos.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.partidos.exception.ResourceNotFoundException;
import uol.compass.partidos.dto.AssociadoDTO;
import uol.compass.partidos.dto.form.AssociadoFormDTO;
import uol.compass.partidos.entity.Associado;
import uol.compass.partidos.repository.AssociadoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    private AssociadoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssociadoDTO save(AssociadoFormDTO body) {
        Associado associado = modelMapper.map(body, Associado.class);
        Associado savedAssociado = this.repository.save(associado);

        return modelMapper.map(savedAssociado, AssociadoDTO.class);
    }

    @Override
    public List<AssociadoDTO> getAssociados() {
        List<Associado> associados = this.repository.findAll();

        return associados
                .stream()
                .map(associado -> modelMapper
                        .map(associado, AssociadoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AssociadoDTO searchAssociado(Long id) {
        Optional<Associado> associado = this.repository.findById(id);

        if (associado.isPresent()) {
            return modelMapper.map(associado.get(), AssociadoDTO.class);
        }

        throw new ResourceNotFoundException("ID não encontrado");
    }

    @Override
    public AssociadoDTO updateAssociado(AssociadoFormDTO body) {
        return null;
    }

    @Override
    public AssociadoDTO deleteAssociado(Long id) {
        return null;
    }

    @Override
    public AssociadoDTO addFiliacao(Long id, Long idPartido) {
        return null;
    }

    @Override
    public AssociadoDTO removeFiliacao(Long id, Long idPartido) {
        return null;
    }
}