package uol.compass.partidos.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AssociadoControllerTest {

    static String ASSOCIADO_URI = "/associados";

    @Autowired
    private MockMvc mvc;

    /* Teste POST */

    @Test
    @DisplayName("Deve criar um associado com sucesso")
    void saveAssociadoTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"Presidente\"," +
                "\"dataNasc\":\"08/09/1925\"," +
                "\"sexo\":\"Masculino\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("nome").value("Merkin Muffley"))
                .andExpect(jsonPath("cargo").value("Presidente"))
                .andExpect(jsonPath("dataNasc").value("08/09/1925"))
                .andExpect(jsonPath("sexo").value("Masculino"));
    }

    /* Testes POST de campos vazios */

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando o nome estiver vazio")
    void saveAssociadoWithEmptyNomeTest() throws Exception {
        String json = "{" +
                "\"nome\":\"\"," +
                "\"cargo\":\"Presidente\"," +
                "\"dataNasc\":\"08/09/1925\"," +
                "\"sexo\":\"Masculino\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando o cargo estiver vazio")
    void saveAssociadoWithEmptyCargoTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"\"," +
                "\"dataNasc\":\"08/09/1925\"," +
                "\"sexo\":\"Masculino\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando dataNasc estiver vazio")
    void saveAssociadoWithEmptyDataNascTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"Presidente\"," +
                "\"dataNasc\":\"\"," +
                "\"sexo\":\"Masculino\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando o sexo estiver vazio")
    void saveAssociadoWithEmptySexoTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"Presidente\"," +
                "\"dataNasc\":\"08/09/1925\"," +
                "\"sexo\":\"\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(status().isBadRequest());
    }

    /* Testes POST com valores inválidos */

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando o cargo for valor inválido")
    void saveAssociadoWithInvalidCargoTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"Imperador\"," +
                "\"dataNasc\":\"08/09/1925\"," +
                "\"sexo\":\"Masculino\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando dataNasc for valor inválido")
    void saveAssociadoWithInvalidDataNascTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"Presidente\"," +
                "\"dataNasc\":\"1925.09.08\"," +
                "\"sexo\":\"Masculino\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando o sexo for valor inválido")
    void saveAssociadoWithInvalidSexoTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"Presidente\"," +
                "\"dataNasc\":\"08/09/1925\"," +
                "\"sexo\":\"Nenhum\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(status().isBadRequest());
    }
}