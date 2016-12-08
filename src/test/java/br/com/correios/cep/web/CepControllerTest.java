package br.com.correios.cep.web;

import br.com.correios.cep.domain.Cep;
import br.com.correios.cep.service.CepService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CepController.class)
public class CepControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CepService cepService;

    public static Cep fixture() {
        Cep cep = new Cep();
        cep.setCep("12345678");
        cep.setBairro("neighborhood");
        cep.setCidade("city");
        cep.setLogradouro("street");
        cep.setTipoLogradouro("rua");
        cep.setUf("state");
        return cep;
    }

    @Test
    public void read() throws Exception {

        given(cepService.findOne("12345678")).willReturn(Optional.of(fixture()));

        mvc.perform(get("/cep/12345678").accept(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(fixture())));
    }

}