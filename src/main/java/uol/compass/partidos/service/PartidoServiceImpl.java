package uol.compass.partidos.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import uol.compass.partidos.dto.AssociadoDTO;
import uol.compass.partidos.dto.PartidoDTO;
import uol.compass.partidos.dto.form.PartidoFormDTO;
import uol.compass.partidos.entity.Partido;
import uol.compass.partidos.exception.ResourceNotFoundException;
import uol.compass.partidos.repository.AssociadoRepository;
import uol.compass.partidos.repository.PartidoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PartidoDTO save(PartidoFormDTO body) {
        Partido partido = modelMapper.map(body, Partido.class);
        Partido savedPartido = this.partidoRepository.save(partido);
        return modelMapper.map(savedPartido, PartidoDTO.class);
    }

    @Override
    public List<PartidoDTO> getPartidos() {
        List<Partido> partidos = this.partidoRepository.findAll();

        return partidos
                .stream()
                .map(partido -> modelMapper
                        .map(partido, PartidoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PartidoDTO searchPartido(Long id) {
        Optional<Partido> partido = this.partidoRepository.findById(id);

        if (partido.isPresent()) {
            return modelMapper.map(partido.get(), PartidoDTO.class);
        }

        throw new ResourceNotFoundException("ID não encontrado");
    }

    @Override
    public PartidoDTO updatePartido(Long id, PartidoFormDTO body) {
        Optional<Partido> partido = this.partidoRepository.findById(id);

        if (partido.isPresent()) {
            Partido updatedPartido = modelMapper.map(body, Partido.class);
            updatedPartido.setId(id);
            this.partidoRepository.save(updatedPartido);

            return modelMapper.map(updatedPartido, PartidoDTO.class);
        }

        throw new ResourceNotFoundException("ID não encontrado");
    }

    @Override
    public PartidoDTO deletePartido(Long id) {
        Optional<Partido> partido = this.partidoRepository.findById(id);

        if (partido.isPresent()) {
            this.partidoRepository.deleteById(id);
            return modelMapper.map(partido.get(), PartidoDTO.class);
        }

        throw new ResourceNotFoundException("ID não encontrado");
    }

    @Override
    public List<AssociadoDTO> getAssociadosPartido(Long id) {
        return null;
    }
}
