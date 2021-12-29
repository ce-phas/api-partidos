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
import uol.compass.partidos.entity.Partido;
import uol.compass.partidos.entity.enums.Ideologia;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test-emptydb")
class PartidoRepositoryTest {

    final Ideologia DEFAULT_IDEOLOGIA = Ideologia.CENTRO;

    private Partido partido;

    @Autowired
    private PartidoRepository repository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    public void createPartido() {
        partido = new Partido();
        partido.setNome("Partido da Burocracia Weberiana");
        partido.setSigla("PBW");
        partido.setIdeologia(DEFAULT_IDEOLOGIA);
        partido.setDataFundacao(LocalDate.now());

        em.persist(partido);
    }

    @Test
    @DisplayName("Deve carregar uma lista de partidos ao buscar por uma ideologia válida")
    void findByValidIdeologiaTest() {
        List<Partido> partidos = repository.findByIdeologia(DEFAULT_IDEOLOGIA);

        assertFalse(partidos.isEmpty());
        assertEquals(partidos.get(0).getNome(), partido.getNome());
        assertEquals(partidos.get(0).getSigla(), partido.getSigla());
        assertEquals(partidos.get(0).getIdeologia(), partido.getIdeologia());
        assertEquals(partidos.get(0).getDataFundacao(), partido.getDataFundacao());
    }

    @Test
    @DisplayName("Deve carregar uma lista vazia ao buscar por uma ideologia válida, mas ausente")
    void findByAbsentIdeologiaTest() {
        Ideologia ideologia = Ideologia.DIREITA;

        List<Partido> partidos = repository.findByIdeologia(ideologia);

        assertTrue(partidos.isEmpty());
    }
}