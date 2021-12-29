package uol.compass.partidos.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uol.compass.partidos.dto.PartidoComAssociadosDTO;
import uol.compass.partidos.dto.PartidoDTO;
import uol.compass.partidos.dto.form.PartidoFormDTO;
import uol.compass.partidos.entity.enums.Ideologia;
import uol.compass.partidos.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PartidoServiceImplTest {

    final String TEST_NOME = "Partido da Burocracia Weberiana";
    final String TEST_SIGLA = "PBW";
    final Ideologia TEST_IDEOLOGIA = Ideologia.CENTRO;
    final LocalDate TEST_DATAFUNDACAO = LocalDate.now();
    final Long TEST_VALID_ID = 5L;
    final Long TEST_INVALID_ID = -1L;

    private PartidoDTO testPartido;
    private PartidoFormDTO testUpdatedPartido;

    @Autowired
    private PartidoServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    void createPartido() {
        testPartido = new PartidoDTO();
        testPartido.setNome(TEST_NOME);
        testPartido.setSigla(TEST_SIGLA);
        testPartido.setIdeologia(TEST_IDEOLOGIA);
        testPartido.setDataFundacao(TEST_DATAFUNDACAO);
    }

    void createUpdatedPartido() {
        testUpdatedPartido = new PartidoFormDTO();
        testUpdatedPartido.setNome("Partido Destro");
        testUpdatedPartido.setSigla("PD");
        testUpdatedPartido.setIdeologia(Ideologia.ESQUERDA);
        testUpdatedPartido.setDataFundacao(LocalDate.of(1950, 12, 31));
    }

    @Test
    @DisplayName("Deve salvar um partido")
    @Order(1)
    void saveTest() {
        createPartido();

        PartidoFormDTO partido = mapper.map(testPartido, PartidoFormDTO.class);
        PartidoDTO savedPartido = service.save(partido);

        assertEquals(TEST_NOME, savedPartido.getNome());
        assertEquals(TEST_SIGLA, savedPartido.getSigla());
        assertEquals(TEST_IDEOLOGIA, savedPartido.getIdeologia());
        assertEquals(TEST_DATAFUNDACAO, savedPartido.getDataFundacao());
    }

    @Test
    @DisplayName("Deve retornar uma lista de partidos")
    @Order(2)
    void getPartidosTest() {
        List<PartidoDTO> partidos = service.getPartidos(null);

        assertFalse(partidos.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar uma lista com dois partidos filtrado pela ideologia")
    @Order(3)
    void getPartidosByIdeologiaTest() {
        List<PartidoDTO> partidos = service.getPartidos("Centro");

        assertEquals(3, partidos.size());
        assertEquals(TEST_NOME, partidos.get(2).getNome());
        assertEquals(TEST_IDEOLOGIA, partidos.get(2).getIdeologia());
    }

    @Test
    @DisplayName("Deve retornar o partido inserido no teste ao procurar por seu ID")
    @Order(4)
    void searchPartidoWithValidIdTest() {
        PartidoDTO partido = service.searchPartido(TEST_VALID_ID);

        assertNotNull(partido);
        assertEquals(TEST_NOME, partido.getNome());
        assertEquals(TEST_SIGLA, partido.getSigla());
        assertEquals(TEST_IDEOLOGIA, partido.getIdeologia());
        assertEquals(TEST_DATAFUNDACAO, partido.getDataFundacao());
    }

    @Test
    @DisplayName("Deve lançar erro ao procurar por ID inválido")
    @Order(5)
    void searchPartidoWithInvalidIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.searchPartido(TEST_INVALID_ID));
    }

    @Test
    @DisplayName("Deve atualizar o partido do teste ao informar ID válido")
    @Order(6)
    void updatePartidoWithValidIdTest() {
        createUpdatedPartido();

        PartidoDTO updatedPartido = service.updatePartido(TEST_VALID_ID, testUpdatedPartido);
        PartidoDTO partido = service.searchPartido(TEST_VALID_ID);

        assertNotNull(updatedPartido);
        assertEquals(partido.getNome(), updatedPartido.getNome());
        assertEquals(partido.getSigla(), updatedPartido.getSigla());
        assertEquals(partido.getIdeologia(), updatedPartido.getIdeologia());
        assertEquals(partido.getDataFundacao(), updatedPartido.getDataFundacao());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar atualizar com ID inválido")
    @Order(7)
    void updatePartidoWithInvalidIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.updatePartido(TEST_INVALID_ID, testUpdatedPartido));
    }

    @Test
    @DisplayName("Deve retornar partido com lista de associados ao procurar por ID válido")
    @Order(8)
    void getAssociadosPartidoWithValidIdTest() {
        PartidoComAssociadosDTO partido = service.getAssociadosPartido(3L);

        assertNotNull(partido);
        assertFalse(partido.getAssociados().isEmpty());
        //assertEquals("Simone Tebet", partido.getAssociados().get(0).getNome());
    }

    @Test
    @DisplayName("Deve lançar erro ao buscar associados do partido com ID inválido")
    @Order(9)
    void getAssociadosPartidoWithInvalidIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.getAssociadosPartido(TEST_INVALID_ID));
    }

    @Test
    @DisplayName("Deve remover partido com ID válido")
    @Order(10)
    void deletePartidoTest() {
        PartidoDTO partido = service.deletePartido(TEST_VALID_ID);

        assertNotNull(partido);
        assertThrows(ResourceNotFoundException.class, () -> service.searchPartido(TEST_VALID_ID));
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar remover partido com ID inválido")
    @Order(11)
    void deletePartidoWithInvalidIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.deletePartido(TEST_INVALID_ID));
    }
}