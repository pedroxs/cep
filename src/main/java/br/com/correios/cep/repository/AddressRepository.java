package br.com.correios.cep.repository;

import br.com.correios.cep.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Address repository.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
