package com.jardessouza.desafio.endereco.repository;

import com.jardessouza.desafio.endereco.entity.Endereco;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findAllByPessoa(Pessoa pessoa);
}
