package com.jardessouza.desafio.dto;

import com.jardessouza.desafio.enums.PrioridadeEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDTO {

    private Long id;

    @NotBlank
    @Max(100)
    private String logadouro;

    @NotNull
    @Max(8)
    private Long cep;

    @NotNull
    @Max(10)
    private Long numero;

    @NotBlank
    @Max(25)
    private String cidade;

    @NotNull
    private PrioridadeEndereco prioridadeEndereco;
}
