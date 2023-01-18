package com.jardessouza.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponseDTO {

    private Long id;

    private String nome;

    private LocalDate dataNascimento;
}
