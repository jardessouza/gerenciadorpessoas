package com.jardessouza.desafio.repository;

import com.jardessouza.desafio.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
