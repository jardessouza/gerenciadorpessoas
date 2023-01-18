package com.jardessouza.desafio.repository;

import com.jardessouza.desafio.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome(String nome);
}
