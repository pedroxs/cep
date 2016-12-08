package br.com.correios.cep.service;

import br.com.correios.cep.domain.Cep;
import br.com.correios.cep.repository.CepRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CepServiceTest {

    @Mock
    private CepRepository cepRepository;
    private CepService cepService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cepService = new CepService(cepRepository);
    }

    @Test
    public void findOneSuccess() throws Exception {
        when(cepRepository.findOne(anyString())).thenReturn(null, new Cep());
        Optional<Cep> cep = cepService.findOne("12345578");

        verify(cepRepository, times(1)).findOne("12345578");
        verify(cepRepository, times(1)).findOne("12345570");
        assertTrue(cep.isPresent());
    }

    @Test
    public void findOneFailure() throws Exception {
        when(cepRepository.findOne(anyString())).thenReturn(null);
        Optional<Cep> cep = cepService.findOne("12345578");

        verify(cepRepository, times(1)).findOne("12345578");
        verify(cepRepository, times(1)).findOne("12345570");
        verify(cepRepository, times(1)).findOne("12345500");
        verify(cepRepository, times(1)).findOne("12345000");
        assertFalse(cep.isPresent());
    }

}