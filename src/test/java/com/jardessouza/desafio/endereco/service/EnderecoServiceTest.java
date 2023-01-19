package com.jardessouza.desafio.endereco.service;

import com.jardessouza.desafio.endereco.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.endereco.dto.EnderecoResponseDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaRequestDTO;
import com.jardessouza.desafio.endereco.builder.EnderecoDTOBuilder;
import com.jardessouza.desafio.endereco.entity.Endereco;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import com.jardessouza.desafio.pessoa.mapper.PessoaMapper;
import com.jardessouza.desafio.pessoa.builder.PessoaDTOBuilder;
import com.jardessouza.desafio.endereco.repository.EnderecoRepository;
import com.jardessouza.desafio.pessoa.service.PessoaService;
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
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepositoryMock;

    @Mock
    private PessoaService pessoaServiceMock;

    private EnderecoDTOBuilder enderecoDTOBuilder;

    private PessoaDTOBuilder pessoaDTOBuilder;
    @BeforeEach
    void setUp(){
        enderecoDTOBuilder = EnderecoDTOBuilder.builder().build();
        pessoaDTOBuilder = PessoaDTOBuilder.builder().build();

        BDDMockito.when(this.pessoaServiceMock.salvarPessoa(ArgumentMatchers.any(PessoaRequestDTO.class)))
                        .thenReturn(PessoaMapper.INSTANCE.toDTO(pessoaDTOBuilder.construirPessoaDTO()));

        BDDMockito.when(this.pessoaServiceMock.localizarEobterPessoa(ArgumentMatchers.anyLong()))
                        .thenReturn(pessoaDTOBuilder.criarPessoa());

        BDDMockito.when(this.pessoaServiceMock.localizarEobterPessoa(
                ArgumentMatchers.eq(pessoaDTOBuilder.criarPessoa().getNome())))
                        .thenReturn(new Pessoa());

        BDDMockito.when(this.enderecoRepositoryMock.save(ArgumentMatchers.any(Endereco.class)))
                .thenReturn(enderecoDTOBuilder.criarEndereco());

        BDDMockito.when(this.enderecoRepositoryMock.findAllByPessoa(ArgumentMatchers.any(Pessoa.class)))
                .thenReturn(List.of(enderecoDTOBuilder.criarEndereco()));
    }

    @Test
    void QuandoObterSucessoSalvarEretornaEndereco(){
        EnderecoRequestDTO enderecoEsperado = enderecoDTOBuilder.construirEndereco();
        EnderecoResponseDTO enderecoCriado
                = this.enderecoService.create("Jardes Souza", enderecoDTOBuilder.construirEndereco());

        Assertions.assertThat(enderecoCriado).isNotNull();
        Assertions.assertThat(enderecoCriado.getId()).isEqualTo(enderecoEsperado.getId());
        Assertions.assertThat(enderecoCriado.getCep()).isEqualTo(enderecoEsperado.getCep());
    }

    @Test
    void QuandoObterSucessoRetornaListaComEnderecosPessoa(){
        EnderecoRequestDTO enderecoEsperado = enderecoDTOBuilder.construirEndereco();
        List<EnderecoResponseDTO> enderecosPessoa =
                this.enderecoService.listarEnderecosDaPessoa("Jardes Souza");

        Assertions.assertThat(enderecosPessoa.get(0).getId()).isNotNull();
        Assertions.assertThat(enderecosPessoa.get(0).getCidade()).isEqualTo(enderecoEsperado.getCidade());
    }
}
