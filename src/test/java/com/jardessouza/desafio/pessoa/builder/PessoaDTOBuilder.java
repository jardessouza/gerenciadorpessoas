package com.jardessouza.desafio.pessoa.builder;

import com.jardessouza.desafio.pessoa.dto.PessoaRequestDTO;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class PessoaDTOBuilder {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String nome = "Jardes Souza";
    @Builder.Default
    private LocalDate dataNascimento = LocalDate.of(1988,12,12);

    public PessoaRequestDTO construirPessoaDTO(){
        return new PessoaRequestDTO(
                id,
                nome,
                dataNascimento
        );
    }
    public Pessoa criarPessoa(){
        return Pessoa.builder()
                .id(id)
                .nome(nome)
                .dataNascimento(dataNascimento)
                .build();
    }
}
