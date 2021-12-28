package uol.compass.partidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.partidos.entity.Associado;
import uol.compass.partidos.entity.enums.Cargo;

import java.util.List;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    List<Associado> findByCargo(Cargo cargo);
}
