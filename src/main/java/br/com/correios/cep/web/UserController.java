package br.com.correios.cep.web;

import br.com.correios.cep.domain.User;
import br.com.correios.cep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * List response entity.
     *
     * @param pageable the pageable
     * @return the response entity
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<User>> list(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.list(pageable));
    }

    /**
     * Create response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User result = userService.create(user);
        return ResponseEntity.created(URI.create("/users/" + result.getEmail())).body(result);
    }

    /**
     * Read response entity.
     *
     * @param email the email
     * @return the response entity
     */
    @RequestMapping(value = "/{email:.+}", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> read(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.read(email));
    }

    /**
     * Update response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @RequestMapping(value = "/{email:.+}", method = PUT, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    /**
     * Delete response entity.
     *
     * @param email the email
     * @return the response entity
     */
    @RequestMapping(value = "/{email:.+}", method = DELETE)
    public ResponseEntity<?> delete(@PathVariable("email") String email) {
        userService.delete(email);
        return ResponseEntity.noContent().build();
    }

}
