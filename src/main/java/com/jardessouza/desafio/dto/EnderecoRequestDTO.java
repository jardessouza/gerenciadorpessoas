package com.jardessouza.desafio.dto;

import com.jardessouza.desafio.enums.PrioridadeEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDTO {

    private Long id;

    @NotBlank
    private String logadouro;

    @NotNull
    private Long cep;

    @NotNull
    private Long numero;

    @NotBlank
    private String cidade;

    @NotNull
    private Long pessoaId;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private PrioridadeEndereco prioridadeEndereco;
}
