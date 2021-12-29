package uol.compass.partidos.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uol.compass.partidos.entity.Associado;
import uol.compass.partidos.entity.enums.Cargo;
import uol.compass.partidos.entity.enums.Sexo;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class AssociadoRepositoryTest {

    final Cargo DEFAULT_CARGO = Cargo.PREFEITO;

    private Associado associado;

    @Autowired
    private AssociadoRepository repository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    public void createAssociado() {
        associado = new Associado();
        associado.setNome("Fulano de Tal");
        associado.setCargo(DEFAULT_CARGO);
        associado.setDataNasc(LocalDate.now());
        associado.setSexo(Sexo.MASCULINO);

        em.persist(associado);
    }

    @Test
    @DisplayName("Deve carregar uma lista de associados ao buscar por um cargo válido")
    public void findByValidCargoTest() {
        List<Associado> associados = repository.findByCargo(DEFAULT_CARGO);

        assertFalse(associados.isEmpty());
        assertEquals(associados.get(0).getNome(), associado.getNome());
        assertEquals(associados.get(0).getCargo(), associado.getCargo());
        assertEquals(associados.get(0).getDataNasc(), associado.getDataNasc());
        assertEquals(associados.get(0).getSexo(), associado.getSexo());
    }

    @Test
    @DisplayName("Deve carregar uma lista vazia ao buscar por um cargo válido, mas ausente")
    public void findByAbsentCargoTest() {
        Cargo cargo = Cargo.VEREADOR;

        List<Associado> associados = repository.findByCargo(cargo);

        assertTrue(associados.isEmpty());
    }

    @Test
    @DisplayName("Deve lançar erro ao buscar por um cargo inválido")
    public void findByInvalidCargoTest() {
        String cargo = "CARGO";

        assertThrows(IllegalArgumentException.class, () -> {
            repository.findByCargo(Cargo.valueOf(cargo));
        });
    }
}