package com.jardessouza.desafio.endereco.builder;

import com.jardessouza.desafio.endereco.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.endereco.entity.Endereco;
import com.jardessouza.desafio.endereco.enums.PrioridadeEndereco;
import com.jardessouza.desafio.pessoa.builder.PessoaDTOBuilder;
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
                .pessoa(PessoaDTOBuilder.builder().build().criarPessoa())
                .build();
    }
    public Endereco criarEnderecoSemId(){
        return Endereco.builder()
                .logadouro(logadouro)
                .cep(cep)
                .numero(numero)
                .cidade(cidade)
                .pessoa(PessoaDTOBuilder.builder().build().criarPessoa())
                .build();
    }

    public Endereco criarEnderecoSemIdEPessoa(){
        return Endereco.builder()
                .logadouro(logadouro)
                .cep(cep)
                .numero(numero)
                .cidade(cidade)
                .prioridadeEndereco(PrioridadeEndereco.EnderecoPrincipal)
                .build();
    }
}
