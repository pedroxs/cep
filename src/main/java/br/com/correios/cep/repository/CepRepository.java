package br.com.correios.cep.repository;

import br.com.correios.cep.domain.Cep;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Cep repository.
 */
public interface CepRepository extends JpaRepository<Cep, String> {
}
