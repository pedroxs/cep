package br.com.correios.cep.web;

import br.com.correios.cep.domain.Address;
import br.com.correios.cep.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * The type Address controller.
 */
@RestController
@RequestMapping("/users/{email:.+}/addresses")
public class AddressController {

    private final AddressService addressService;

    /**
     * Instantiates a new Address controller.
     *
     * @param addressService the address service
     */
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Address>> list(@PathVariable("email") String email) {
        return ResponseEntity.ok(addressService.list(email));
    }

    /**
     * Create response entity.
     *
     * @param email   the email
     * @param address the address
     * @return the response entity
     */
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Address> create(@PathVariable("email") String email, @Valid @RequestBody Address address) {
        Address result = addressService.create(email, address);
        return ResponseEntity
                .created(URI.create("/user/" + email + "/address/" + result.getId()))
                .body(result);
    }

    /**
     * Read response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Address> read(@PathVariable("id") Long id) {
        return ResponseEntity.ok(addressService.read(id));
    }

    /**
     * Update response entity.
     *
     * @param address the address
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = PUT, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Address> update(@Valid @RequestBody Address address) {
        return ResponseEntity.ok(addressService.update(address));
    }

    /**
     * Delete response entity.
     *
     * @param email the email
     * @param id    the id
     * @return the response entity
     */
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<?> delete(@PathVariable("email") String email, @PathVariable("id") Long id) {
        addressService.delete(email, id);
        return ResponseEntity.noContent().build();
    }
}
