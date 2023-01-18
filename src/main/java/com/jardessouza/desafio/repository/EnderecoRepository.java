package com.jardessouza.desafio.repository;

import com.jardessouza.desafio.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.entity.Endereco;
import com.jardessouza.desafio.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findAllByPessoa(Pessoa pessoa);
}
