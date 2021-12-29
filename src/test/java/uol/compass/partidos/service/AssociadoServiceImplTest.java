package uol.compass.partidos.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uol.compass.partidos.dto.AssociadoComPartidoDTO;
import uol.compass.partidos.dto.AssociadoDTO;
import uol.compass.partidos.dto.form.AssociadoFormDTO;
import uol.compass.partidos.dto.form.FiliacaoFormDTO;
import uol.compass.partidos.entity.enums.Cargo;
import uol.compass.partidos.entity.enums.Sexo;
import uol.compass.partidos.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AssociadoServiceImplTest {

    final String TEST_NOME = "Aalzira da Silva";
    final Cargo TEST_CARGO = Cargo.PREFEITO;
    final LocalDate TEST_DATANASC = LocalDate.now();
    final Sexo TEST_SEXO = Sexo.FEMININO;
    final Long TEST_VALID_ID = 4L;
    final Long TEST_INVALID_ID = -1L;

    private AssociadoDTO testAssociado;
    private AssociadoFormDTO testUpdatedAssociado;

    @Autowired
    private AssociadoServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    void createAssociado() {
        testAssociado = new AssociadoDTO();
        testAssociado.setNome(TEST_NOME);
        testAssociado.setCargo(TEST_CARGO);
        testAssociado.setDataNasc(TEST_DATANASC);
        testAssociado.setSexo(TEST_SEXO);
    }

    void createUpdatedAssociado() {
        testUpdatedAssociado = new AssociadoFormDTO();
        testUpdatedAssociado.setNome("Fulano de Tal");
        testUpdatedAssociado.setCargo(Cargo.DEPUTADO_FEDERAL);
        testUpdatedAssociado.setDataNasc(LocalDate.of(1950, 12, 31));
        testUpdatedAssociado.setSexo(Sexo.MASCULINO);
    }

    @Test
    @DisplayName("Deve salvar um associado")
    @Order(1)
    void saveTest() {
        createAssociado();

        AssociadoFormDTO associado = modelMapper.map(testAssociado, AssociadoFormDTO.class);
        AssociadoDTO savedAssociado = service.save(associado);

        assertEquals(TEST_NOME, savedAssociado.getNome());
        assertEquals(TEST_CARGO, savedAssociado.getCargo());
        assertEquals(TEST_DATANASC, savedAssociado.getDataNasc());
        assertEquals(TEST_SEXO, savedAssociado.getSexo());
    }

    @Test
    @DisplayName("Deve retornar uma lista de associados")
    @Order(2)
    void getAssociadosTest() {
        List<AssociadoDTO> associados = service.getAssociados(null, false);

        assertNotNull(associados);
    }

    @Test
    @DisplayName("Deve retornar uma lista com um único associado filtrado pelo cargo")
    @Order(3)
    void getAssociadosByCargoTest() {
        List<AssociadoDTO> associados = service.getAssociados("Prefeito", false);

        assertEquals(1, associados.size());
        assertEquals(TEST_NOME, associados.get(0).getNome());
        assertEquals(TEST_CARGO, associados.get(0).getCargo());
    }

    @Test
    @DisplayName("Deve retornar uma lista de associados ordenados pelo nome")
    @Order(4)
    void getAssociadosSortedTest() {
        List<AssociadoDTO> associados = service.getAssociados(null, true);

        assertNotNull(associados);
        assertEquals(TEST_NOME, associados.get(0).getNome());
        assertEquals("Efraim Filho", associados.get(1).getNome());
        assertEquals("Fátima Bezerra", associados.get(2).getNome());
        assertEquals("Simone Tebet", associados.get(3).getNome());
    }

    @Test
    @DisplayName("Deve retornar o associado inserido no teste ao procurar por seu ID")
    @Order(5)
    void searchAssociadoWithValidIdTest() {
        AssociadoDTO associado = service.searchAssociado(TEST_VALID_ID);

        assertNotNull(associado);
        assertEquals(TEST_NOME, associado.getNome());
        assertEquals(TEST_CARGO, associado.getCargo());
        assertEquals(TEST_DATANASC, associado.getDataNasc());
        assertEquals(TEST_SEXO, associado.getSexo());
    }

    @Test
    @DisplayName("Deve lançar erro ao procurar por ID inválido")
    @Order(6)
    void searchAssociadoWithInvalidIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.searchAssociado(TEST_INVALID_ID));
    }

    @Test
    @DisplayName("Deve atualizar o associado do teste ao informar ID válido")
    @Order(7)
    void updateAssociadoWithValidIdTest() {
        createUpdatedAssociado();

        AssociadoDTO updatedAssociado = service.updateAssociado(TEST_VALID_ID, testUpdatedAssociado);
        AssociadoDTO associado = service.searchAssociado(TEST_VALID_ID);

        assertNotNull(updatedAssociado);
        assertEquals(associado.getNome(), updatedAssociado.getNome());
        assertEquals(associado.getCargo(), updatedAssociado.getCargo());
        assertEquals(associado.getDataNasc(), updatedAssociado.getDataNasc());
        assertEquals(associado.getSexo(), updatedAssociado.getSexo());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar atualizar com ID inválido")
    @Order(8)
    void updateAssociadoWithInvalidIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.updateAssociado(TEST_INVALID_ID, testUpdatedAssociado));
    }

    @Test
    @DisplayName("Deve vincular um partido a um associado quando IDs forem válidos")
    @Order(9)
    void addFiliacaoWithValidIdsTest() {
        FiliacaoFormDTO filiacaoForm = new FiliacaoFormDTO();

        filiacaoForm.setIdAssociado(TEST_VALID_ID);
        filiacaoForm.setIdPartido(1L);

        AssociadoComPartidoDTO associado = service.addFiliacao(filiacaoForm);

        assertNotNull(associado);
        assertNotNull(associado.getPartido());
        assertEquals("Avante", associado.getPartido().getNome());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar filiar com ID de associado inválido")
    @Order(10)
    void addFiliacaoWithInvalidAssociadoIdTest() {
        FiliacaoFormDTO filiacaoForm = new FiliacaoFormDTO();

        filiacaoForm.setIdAssociado(TEST_INVALID_ID);
        filiacaoForm.setIdPartido(1L);

        assertThrows(ResourceNotFoundException.class, () -> service.addFiliacao(filiacaoForm));
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar filiar com ID de partido inválido")
    @Order(11)
    void addFiliacaoWithInvalidPartidoIdTest() {
        FiliacaoFormDTO filiacaoForm = new FiliacaoFormDTO();

        filiacaoForm.setIdAssociado(TEST_VALID_ID);
        filiacaoForm.setIdPartido(-1L);

        assertThrows(ResourceNotFoundException.class, () -> service.addFiliacao(filiacaoForm));
    }

    @Test
    @DisplayName("Deve desvincular um partido a um associado quando ID for válido")
    @Order(12)
    void removeFiliacaoWithValidIdTest() {
        AssociadoComPartidoDTO associado = service.removeFiliacao(TEST_VALID_ID);

        assertNotNull(associado);
        assertNull(associado.getPartido());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar desvincular com ID de associado inválido")
    @Order(13)
    void removeFiliacaoWithInvalidAssociadoIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.removeFiliacao(TEST_INVALID_ID));
    }

    @Test
    @DisplayName("Deve remover associado com ID válido")
    @Order(14)
    void deleteAssociadoTest() {
        AssociadoDTO associado = service.deleteAssociado(TEST_VALID_ID);

        assertNotNull(associado);
        assertThrows(ResourceNotFoundException.class, () -> service.searchAssociado(TEST_VALID_ID));
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar remover associado com ID inválido")
    @Order(15)
    void deleteAssociadoWithInvalidIdTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.deleteAssociado(TEST_INVALID_ID));
    }
}