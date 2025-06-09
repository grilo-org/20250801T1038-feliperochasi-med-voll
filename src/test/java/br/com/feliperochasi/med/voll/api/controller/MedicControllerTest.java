package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.domain.address.Address;
import br.com.feliperochasi.med.voll.api.domain.address.DataAddress;
import br.com.feliperochasi.med.voll.api.domain.medic.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RegisterDataMedic> registerDataMedicJson;

    @Autowired
    private JacksonTester<DetailsDataMedic> detailsDataMedicJson;

    @MockBean
    private MedicRepository medicRepository;

    @Test
    @DisplayName("Deveria retornar 400 caso o corpo da requisao esteja invalido")
    @WithMockUser
    void registerFirst() throws Exception {
        var response = mvc.perform(
                post("/medicos")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar 200 quando o registro de um novo medico for concluido com sucesso")
    @WithMockUser
    void registerSecond() throws Exception {
        var registerMedicData = new RegisterDataMedic("Medico", "medico@voll.med", "999999", Specialised.ORTOPEDIA, "9999999999", dataAddress());
        var detailsMedicData = new DetailsDataMedic(null,"Medico", "medico@voll.med", "999999", "9999999999", Specialised.ORTOPEDIA, address(dataAddress()));

        when(medicRepository.save(any())).thenReturn(new Medic(registerMedicData));

        var response = mvc.perform(
                post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerDataMedicJson.write(registerMedicData).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var expectedJson = detailsDataMedicJson.write(
                detailsMedicData
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    private Address address(DataAddress dataAddress) {
        return new Address(dataAddress);
    }

    private DataAddress dataAddress() {
        return new DataAddress(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}