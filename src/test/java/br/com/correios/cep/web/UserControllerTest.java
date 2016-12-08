package br.com.correios.cep.web;

import br.com.correios.cep.domain.User;
import br.com.correios.cep.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@EnableSpringDataWebSupport
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    public static User fixture() {
        User user = new User();
        user.setEmail("me@domain.com");
        user.setPassword("1234");
        user.setName("John Doe");
        return user;
    }

    @Test
    public void list() throws Exception {
        PageImpl<User> userPage = new PageImpl<>(singletonList(fixture()), new PageRequest(0, 10), 1);
        given(userService.list(any(PageRequest.class)))
                .willReturn(userPage);
        mvc.perform(get("/users")
                .with(user("user"))
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(userPage)));

        verify(userService).list(new PageRequest(0, 10));
    }

    @Test
    public void create() throws Exception {
        given(userService.create(any(User.class))).willReturn(fixture());
        mvc.perform(post("/users")
                .with(user("user"))
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(fixture())))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(fixture())));

        verify(userService).create(Matchers.refEq(fixture()));
    }

    @Test
    public void read() throws Exception {
        given(userService.read("me@domain.com")).willReturn(fixture());
        mvc.perform(get("/users/me@domain.com")
                .with(user("user"))
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(fixture())));

        verify(userService).read("me@domain.com");
    }

    @Test
    public void update() throws Exception {
        given(userService.update(any(User.class))).willReturn(fixture());
        mvc.perform(put("/users/me@domain.com")
                .with(user("user"))
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(fixture())))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(fixture())));

        verify(userService).update(Matchers.refEq(fixture()));
    }

    @Test
    public void deleteOk() throws Exception {
        mvc.perform(delete("/users/{email}", "me@domain.com")
                .with(user("user")))
                .andExpect(status().isNoContent());

        verify(userService).delete("me@domain.com");
    }

}