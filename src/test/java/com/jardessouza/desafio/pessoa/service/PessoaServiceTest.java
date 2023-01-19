package com.jardessouza.desafio.pessoa.service;

import com.jardessouza.desafio.dto.PessoaRequestDTO;
import com.jardessouza.desafio.dto.PessoaResponseDTO;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.mapper.PessoaMapper;
import com.jardessouza.desafio.pessoa.builder.PessoaBuilderDTO;
import com.jardessouza.desafio.repository.PessoaRepository;
import com.jardessouza.desafio.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class PessoaServiceTest {
    private PessoaBuilderDTO pessoaBuilderDTO;
    @InjectMocks
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository pessoaRepositoryMock;

    @BeforeEach
    void setUp(){
        pessoaBuilderDTO = PessoaBuilderDTO.builder().build();
        BDDMockito.when(this.pessoaRepositoryMock.save(ArgumentMatchers.any(Pessoa.class)))
                .thenReturn(PessoaMapper.INSTANCE.toModel(pessoaBuilderDTO.construirPessoaDTO()));

        BDDMockito.when(this.pessoaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(pessoaBuilderDTO.criarPessoa()));

        BDDMockito.when(this.pessoaRepositoryMock.findByNome(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(pessoaBuilderDTO.criarPessoa()));

        BDDMockito.when(this.pessoaRepositoryMock.findAll())
                .thenReturn(List.of(pessoaBuilderDTO.criarPessoa()));

    }

    @Test
    void QuandoObterSucessoRetornaPessoa(){
        PessoaRequestDTO pessoaEsperada = pessoaBuilderDTO.construirPessoaDTO();
        PessoaResponseDTO pessoaCriada =
                this.pessoaService.salvarPessoa(pessoaBuilderDTO.construirPessoaDTO());

        Assertions.assertThat(pessoaCriada.getId()).isNotNull();
        Assertions.assertThat(pessoaCriada.getNome()).isEqualTo(pessoaEsperada.getNome());
    }

    @Test
    void QuandoObterSucessoAtualizarPessoa(){
        Assertions.assertThatCode(() -> this.pessoaService
                        .editarPessoa(1L, pessoaBuilderDTO.construirPessoaDTO()))
                        .doesNotThrowAnyException();
    }

    @Test
    void QuandoNaoObterSucessoRetornarExcessao(){
        BDDMockito.when(this.pessoaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> this.pessoaService.verificarSePessoaExiste(1L));
    }

    @Test
    void QuandoBuscarNomeRetornarPessoaComSucesso(){
        Assertions.assertThatCode(() -> this.pessoaService
                .verificarSePessoaExiste("Jardes Souza"))
                .doesNotThrowAnyException();
    }

    @Test
    void QuandoNaoEncontrarNomeRetornarExcessao(){
        BDDMockito.when(this.pessoaRepositoryMock.findByNome(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> this.pessoaService.verificarSePessoaExiste("Jardes Souza"));
    }

    @Test
    void QuantoObterSucessoRetornaListaPessoas(){
        String nomeEsperado = pessoaBuilderDTO.construirPessoaDTO().getNome();
        List<PessoaResponseDTO> listarPessoas = this.pessoaService.listarPessoas();

        Assertions.assertThat(listarPessoas)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(listarPessoas.get(0).getNome()).isEqualTo(nomeEsperado);
    }


}
