package br.com.correios.cep.service;

import br.com.correios.cep.domain.Cep;
import br.com.correios.cep.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Cep service.
 */
@Service
public class CepService {

    private static final int TOTAL_RETRIES = 3;
    private final CepRepository cepRepository;

    /**
     * Instantiates a new Cep service.
     *
     * @param cepRepository the cep repository
     */
    @Autowired
    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    /**
     * Find one optional.
     *
     * @param cep the cep
     * @return the optional
     */
    public Optional<Cep> findOne(String cep) {
        int currentRetry = 1;
        Cep result = cepRepository.findOne(cep);
        while (result == null && currentRetry <= TOTAL_RETRIES) {
            result = cepRepository.findOne(shiftWithZeros(cep, currentRetry));
            currentRetry++;
        }
        return Optional.ofNullable(result);
    }

    private String shiftWithZeros(String s, int i) {
        return s.substring(0, s.length() > i ? s.length() - i : 1).concat(padZeros(i));
    }

    private String padZeros(int amount) {
        String z = "";
        for (int i = 0; i < amount; i++) {
            z = z.concat("0");
        }
        return z;
    }
}
