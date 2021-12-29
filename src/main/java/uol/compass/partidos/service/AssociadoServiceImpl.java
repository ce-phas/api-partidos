package uol.compass.partidos.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uol.compass.partidos.dto.AssociadoComPartidoDTO;
import uol.compass.partidos.dto.form.FiliacaoFormDTO;
import uol.compass.partidos.entity.Partido;
import uol.compass.partidos.entity.enums.Cargo;
import uol.compass.partidos.exception.ResourceNotFoundException;
import uol.compass.partidos.dto.AssociadoDTO;
import uol.compass.partidos.dto.form.AssociadoFormDTO;
import uol.compass.partidos.entity.Associado;
import uol.compass.partidos.repository.AssociadoRepository;
import uol.compass.partidos.repository.PartidoRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    static final String ID_NOT_FOUND = "ID não encontrado";

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssociadoDTO save(AssociadoFormDTO body) {
        Associado associado = modelMapper.map(body, Associado.class);
        Associado savedAssociado = this.associadoRepository.save(associado);
        return modelMapper.map(savedAssociado, AssociadoDTO.class);
    }

    @Override
    public List<AssociadoDTO> getAssociados(String cargo, Boolean sort) {
        List<Associado> associados;

        if (cargo == null) {
            associados = this.associadoRepository.findAll();
        } else {
            associados = this.associadoRepository.findByCargo(
                    Cargo.valueOf(cargo.toUpperCase().replace(' ', '_')));
        }

        if (Boolean.TRUE.equals(sort)) {
            associados.sort(Comparator.comparing(Associado::getNome));
        }

        return associados
                .stream()
                .map(associado -> modelMapper
                        .map(associado, AssociadoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AssociadoDTO searchAssociado(Long id) {
        Optional<Associado> associado = this.associadoRepository.findById(id);

        if (associado.isPresent()) {
            return modelMapper.map(associado.get(), AssociadoDTO.class);
        }

        throw new ResourceNotFoundException(ID_NOT_FOUND);
    }

    @Override
    public AssociadoDTO updateAssociado(Long id, AssociadoFormDTO body) {
        Optional<Associado> associado = this.associadoRepository.findById(id);

        if (associado.isPresent()) {
            Associado updatedAssociado = modelMapper.map(body, Associado.class);
            updatedAssociado.setId(id);
            this.associadoRepository.save(updatedAssociado);

            return modelMapper.map(updatedAssociado, AssociadoDTO.class);
        }

        throw new ResourceNotFoundException(ID_NOT_FOUND);
    }

    @Override
    public AssociadoDTO deleteAssociado(Long id) {
        Optional<Associado> associado = this.associadoRepository.findById(id);

        if (associado.isPresent()) {
            this.associadoRepository.deleteById(id);
            return modelMapper.map(associado.get(), AssociadoDTO.class);
        }

        throw new ResourceNotFoundException(ID_NOT_FOUND);
    }

    @Override
    public AssociadoComPartidoDTO addFiliacao(FiliacaoFormDTO body) {
        Optional<Associado> associadoOptional = this.associadoRepository.findById(body.getIdAssociado());
        Optional<Partido> partidoOptional = this.partidoRepository.findById(body.getIdPartido());

        if (associadoOptional.isEmpty()) {
            throw new ResourceNotFoundException(ID_NOT_FOUND + ": associado");
        }
        if (partidoOptional.isEmpty()) {
            throw new ResourceNotFoundException(ID_NOT_FOUND + ": partido");
        }
        Associado associado = associadoOptional.get();
        Partido partido = partidoOptional.get();

        associado.setPartido(partido);
        this.associadoRepository.save(associado);

        return modelMapper.map(associado, AssociadoComPartidoDTO.class);
    }

    @Override
    public AssociadoComPartidoDTO removeFiliacao(Long id) {
        Optional<Associado> associadoOptional = this.associadoRepository.findById(id);

        if (associadoOptional.isPresent()) {
            Associado associado = associadoOptional.get();
            associado.setPartido(null);
            this.associadoRepository.save(associado);

            return modelMapper.map(associado, AssociadoComPartidoDTO.class);
        }

        throw new ResourceNotFoundException(ID_NOT_FOUND);
    }
}
