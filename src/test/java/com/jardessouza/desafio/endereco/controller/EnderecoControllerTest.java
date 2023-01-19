package com.jardessouza.desafio.endereco.controller;

import com.jardessouza.desafio.controller.EnderecoController;
import com.jardessouza.desafio.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.dto.EnderecoResponseDTO;
import com.jardessouza.desafio.endereco.builder.EnderecoDTOBuilder;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.mapper.EnderecoMapper;
import com.jardessouza.desafio.pessoa.builder.PessoaDTOBuilder;
import com.jardessouza.desafio.service.EnderecoService;
import com.jardessouza.desafio.service.PessoaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class EnderecoControllerTest {
    @InjectMocks
    private EnderecoController enderecoController;
    @Mock
    private EnderecoService enderecoServiceMock;
    @Mock
    private PessoaService pessoaServiceMock;
    private EnderecoDTOBuilder enderecoDTOBuilder;

    private PessoaDTOBuilder pessoaDTOBuilder;

    @BeforeEach
    void setUp(){
        enderecoDTOBuilder = EnderecoDTOBuilder.builder().build();
        pessoaDTOBuilder = PessoaDTOBuilder.builder().build();

        BDDMockito.when(this.pessoaServiceMock.localizarEobterPessoa(ArgumentMatchers.anyLong()))
                .thenReturn(new Pessoa());

        BDDMockito.when(this.pessoaServiceMock.localizarEobterPessoa(
                        ArgumentMatchers.eq(pessoaDTOBuilder.criarPessoa().getNome())))
                .thenReturn(new Pessoa());

        BDDMockito.when(this.enderecoServiceMock.create(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any()
        )).thenReturn(EnderecoMapper.INSTANCE.toDTO(enderecoDTOBuilder.construirEndereco()));

        BDDMockito.when(this.enderecoServiceMock.listarEnderecosDaPessoa(ArgumentMatchers.anyString()))
                .thenReturn(List.of(EnderecoMapper.INSTANCE.toDTO(enderecoDTOBuilder.construirEndereco())));
    }

    @Test
    void QuandoSalvarRetornarEnderecoComSucesso(){
        EnderecoRequestDTO enderecoEsperado = enderecoDTOBuilder.construirEndereco();
        EnderecoResponseDTO enderecoCriado = this.enderecoController
                .criarEnderecoPessoa("Jardes Souza", enderecoDTOBuilder.construirEndereco()).getBody();

        Assertions.assertThat(enderecoCriado.getId()).isNotNull();
        Assertions.assertThat(enderecoCriado.getCidade()).isEqualTo(enderecoEsperado.getCidade());

    }

    @Test
    void QuandoObterSucessoRetornaListaComEnderecosPessoa(){
        EnderecoRequestDTO enderecoEsperado = enderecoDTOBuilder.construirEndereco();
        List<EnderecoResponseDTO> listaEnderecos = this.enderecoController
                .listarEnderecosPessoa("Jardes Souza").getBody();

        Assertions.assertThat(listaEnderecos)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(listaEnderecos.get(0).getId()).isNotNull();
        Assertions.assertThat(listaEnderecos.get(0).getCidade()).isEqualTo(enderecoEsperado.getCidade());
    }
}
