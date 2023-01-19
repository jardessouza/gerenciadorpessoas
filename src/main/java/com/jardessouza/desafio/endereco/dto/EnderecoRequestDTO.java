package com.jardessouza.desafio.endereco.dto;

import com.jardessouza.desafio.endereco.enums.PrioridadeEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private PrioridadeEndereco prioridadeEndereco;
}
