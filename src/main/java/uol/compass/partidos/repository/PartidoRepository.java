package uol.compass.partidos.repository;

import uol.compass.partidos.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.partidos.entity.enums.Ideologia;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
    List<Partido> findByIdeologia(Ideologia ideologia);
}
