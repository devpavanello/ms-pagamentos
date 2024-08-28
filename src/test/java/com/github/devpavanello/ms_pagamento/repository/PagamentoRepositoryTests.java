package com.github.devpavanello.ms_pagamento.repository;

import com.github.devpavanello.ms_pagamento.model.Pagamento;
import com.github.devpavanello.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTests {

    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalPagamento;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 100L;
        countTotalPagamento = 3L;
    }

    @Test
    @DisplayName("Deveria excluir pagamento quando ID existe")
    public void deleteShouldDeleteObjectWhenIdExists(){
        //ACT
        repository.deleteById(existingId);

        //Assert
        Optional<Pagamento> result = repository.findById(existingId);

        //Testa se existe um obj dentro do optional
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

    @Test
    @DisplayName("Deveria salvar objeto com auto incremento quando Id é nulo")
    public void saveShouldPersistWhitAutIncrimentWhenIdIsNull(){

        Pagamento pagamento = Factory.createPagamento();
        pagamento.setId(null);
        pagamento = repository.save(pagamento);
        Assertions.assertNotNull(pagamento.getId());
        //Verifica se é o proximo Id
        Assertions.assertEquals(countTotalPagamento + 1, pagamento.getId());
    }
}
