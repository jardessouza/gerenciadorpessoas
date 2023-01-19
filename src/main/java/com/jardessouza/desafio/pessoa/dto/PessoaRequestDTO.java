package com.jardessouza.desafio.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequestDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataNascimento;
}
