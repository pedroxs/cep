package br.com.correios.cep.service;

import br.com.correios.cep.domain.User;
import br.com.correios.cep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create user.
     *
     * @param user the user
     * @return the user
     */
    public User create(User user) {
        //TODO handle password managing
        return userRepository.save(user);
    }

    /**
     * Read user.
     *
     * @param email the email
     * @return the user
     */
    public User read(String email) {
        return userRepository.findOne(email);
    }

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     */
    public User update(User user) {
        //TODO handle password managing
        return userRepository.save(user);
    }

    /**
     * Delete.
     *
     * @param email the email
     */
    public void delete(String email) {
        userRepository.delete(email);
    }

    /**
     * Page of users.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<User> list(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
