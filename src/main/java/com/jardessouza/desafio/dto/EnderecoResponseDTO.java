package com.jardessouza.desafio.dto;

import com.jardessouza.desafio.enums.PrioridadeEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDTO {

    private Long id;

    private String logadouro;

    private Long cep;

    private Long numero;

    private String cidade;

    PessoaDTO pessoa;

    private PrioridadeEndereco prioridadeEndereco;
}
