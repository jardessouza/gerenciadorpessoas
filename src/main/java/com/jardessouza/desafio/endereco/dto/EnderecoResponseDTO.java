package com.jardessouza.desafio.endereco.dto;

import com.jardessouza.desafio.endereco.enums.PrioridadeEndereco;
import com.jardessouza.desafio.pessoa.dto.PessoaDTO;
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

    private PessoaDTO pessoa;

    private PrioridadeEndereco prioridadeEndereco;
}
