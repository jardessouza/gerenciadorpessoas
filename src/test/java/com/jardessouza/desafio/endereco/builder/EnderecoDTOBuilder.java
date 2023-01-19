package com.jardessouza.desafio.endereco.builder;

import com.jardessouza.desafio.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.entity.Endereco;
import com.jardessouza.desafio.enums.PrioridadeEndereco;
import com.jardessouza.desafio.pessoa.builder.PessoaBuilderDTO;
import lombok.Builder;
@Builder
public class EnderecoDTOBuilder {
    @Builder.Default
    private final Long id = 1L;
    @Builder.Default
    private final String logadouro = "Rua 04";
    @Builder.Default
    private final Long cep = 65065600L;
    @Builder.Default
    private final Long numero = 6L;
    @Builder.Default
    private final String cidade = "Sao Luis";
    @Builder.Default
    private final Long pessoaId = 1L;
    @Builder.Default
    private final PrioridadeEndereco prioridadeEndereco = PrioridadeEndereco.EnderecoPrincipal;

    public EnderecoRequestDTO construirEndereco(){
        return new EnderecoRequestDTO(
                id,
                logadouro,
                cep,
                numero,
                cidade,
                prioridadeEndereco
        );
    }

    public Endereco criarEndereco(){
        return Endereco.builder()
                .id(id)
                .logadouro(logadouro)
                .cep(cep)
                .numero(numero)
                .cidade(cidade)
                .pessoa(PessoaBuilderDTO.builder().build().criarPessoa())
                .build();
    }
}
