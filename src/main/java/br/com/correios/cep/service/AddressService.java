package br.com.correios.cep.service;

import br.com.correios.cep.domain.Address;
import br.com.correios.cep.domain.User;
import br.com.correios.cep.ex.NotFoundException;
import br.com.correios.cep.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * The type Address service.
 */
@Service
public class AddressService {

    private final UserService userService;
    private final AddressRepository addressRepository;

    /**
     * Instantiates a new Address service.
     *
     * @param userService       the user service
     * @param addressRepository the address repository
     */
    @Autowired
    public AddressService(UserService userService, AddressRepository addressRepository) {
        this.userService = userService;
        this.addressRepository = addressRepository;
    }

    /**
     * Create address.
     *
     * @param email   the email
     * @param address the address
     * @return the address
     */
    public Address create(String email, Address address) {
        User user = userService.read(email);
        if (user == null) {
            throw new NotFoundException("User doesn't exist!");
        }
        Address save = addressRepository.save(address);
        user.getAddresses().add(save);
        userService.update(user);
        return save;
    }

    /**
     * Read address.
     *
     * @param id the id
     * @return the address
     */
    public Address read(Long id) {
        //TODO check read permission
        return addressRepository.findOne(id);
    }

    /**
     * Update address.
     *
     * @param address the address
     * @return the address
     */
    public Address update(Address address) {
        //TODO check permission
        return addressRepository.save(address);
    }

    /**
     * Delete.
     *
     * @param email the email
     * @param id    the id
     */
    public void delete(String email, Long id) {
        User user = userService.read(email);
        user.getAddresses().removeIf(address -> address.getId().equals(id));
        userService.update(user);
        addressRepository.delete(id);
    }

    /**
     * List addresses.
     *
     * @param email the email
     * @return the set
     */
    public List<Address> list(String email) {
        return userService.read(email).getAddresses();
    }
}
