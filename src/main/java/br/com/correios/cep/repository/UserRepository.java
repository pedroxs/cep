package br.com.correios.cep.repository;

import br.com.correios.cep.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
