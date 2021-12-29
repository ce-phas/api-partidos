package uol.compass.partidos.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando um ou mais campos estiverem vazios")
    void saveAssociadoWithEmptyFieldsTest() throws Exception {
        String json = "{" +
                "\"nome\":\"\"," +
                "\"cargo\":\"\"," +
                "\"dataNasc\":\"\"," +
                "\"sexo\":\"\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field").value("cargo"))
                .andExpect(jsonPath("$.fieldErrors[1].field").value("dataNasc"))
                .andExpect(jsonPath("$.fieldErrors[2].field").value("nome"))
                .andExpect(jsonPath("$.fieldErrors[3].field").value("sexo"));
    }

    @Test
    @DisplayName("Deve lançar erro de validação ao criar quando cargo ou sexo forem inválidos")
    void saveAssociadoWithInvalidFieldsTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"Imperador\"," +
                "\"dataNasc\":\"08/09/1925\"," +
                "\"sexo\":\"Nenhum\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0].field").value("cargo"))
                .andExpect(jsonPath("$.fieldErrors[1].field").value("sexo"));
    }

    @Test
    @DisplayName("Deve lançar erro ao criar quando o formato de data for inválido")
    void saveAssociadoWithInvalidDataNascTest() throws Exception {
        String json = "{" +
                "\"nome\":\"Merkin Muffley\"," +
                "\"cargo\":\"Presidente\"," +
                "\"dataNasc\":\"1925/08/09\"," +
                "\"sexo\":\"Masculino\"" +
                "}";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ASSOCIADO_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest());
    }
}