package com.jardessouza.desafio.pessoa.repository;

import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.pessoa.builder.PessoaDTOBuilder;
import com.jardessouza.desafio.repository.PessoaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PessoaRepositoryTest {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void LocalizarNomeQuandoObterSucesso(){
        PessoaDTOBuilder pessoaParaSalvar = PessoaDTOBuilder.builder().build();
        Pessoa pessoaSalva = this.pessoaRepository.save(pessoaParaSalvar.criarPessoa());

        Optional<Pessoa> pessoaEncontrada = this.pessoaRepository.findByNome(pessoaSalva.getNome());

        Assertions.assertThat(pessoaEncontrada).isNotEmpty();
        Assertions.assertThat(pessoaEncontrada.get().getNome()).isEqualTo(pessoaSalva.getNome());
    }

}
