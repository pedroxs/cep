package br.com.correios.cep.web;

import br.com.correios.cep.domain.Address;
import br.com.correios.cep.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AddressService addressService;

    public static Address fixture() {
        Address address = new Address();
        address.setCep(CepControllerTest.fixture());
        address.setComplemento("ap 1");
        address.setNumero(1);
        address.setPreferencial(true);
        return address;
    }

    @Test
    public void list() throws Exception {
        given(addressService.list(anyString())).willReturn(singletonList(fixture()));
        mvc.perform(get("/users/me@domain.com/addresses")
                .with(user("user"))
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(singleton(fixture()))));

        verify(addressService).list("me@domain.com");
    }

    @Test
    public void create() throws Exception {
        given(addressService.create(anyString(), any(Address.class))).willReturn(fixture());
        mvc.perform(post("/users/me@domain.com/addresses")
                .with(user("user"))
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(fixture())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

        verify(addressService).create(eq("me@domain.com"), any(Address.class));
    }

    @Test
    public void read() throws Exception {
        given(addressService.read(anyLong())).willReturn(fixture());
        mvc.perform(get("/users/me@domain.com/addresses/1")
                .with(user("user"))
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(fixture())));

        verify(addressService).read(1L);
    }

    @Test
    public void update() throws Exception {
        given(addressService.update(any(Address.class))).willReturn(fixture());
        mvc.perform(put("/users/me@domain.com/addresses/1")
                .with(user("user"))
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(fixture())))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(fixture())));

        verify(addressService).update(any(Address.class));
    }

    @Test
    public void deleteOk() throws Exception {
        mvc.perform(delete("/users/me@domain.com/addresses/1")
                .with(user("user")))
                .andExpect(status().isNoContent());

        verify(addressService).delete("me@domain.com", 1L);
    }

}