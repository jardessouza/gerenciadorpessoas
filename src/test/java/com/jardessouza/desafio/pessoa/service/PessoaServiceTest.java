package com.jardessouza.desafio.pessoa.service;

import com.jardessouza.desafio.pessoa.dto.PessoaRequestDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaResponseDTO;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import com.jardessouza.desafio.pessoa.exception.PessoaJaExisteException;
import com.jardessouza.desafio.pessoa.exception.PessoaNaoEncontradaException;
import com.jardessouza.desafio.pessoa.mapper.PessoaMapper;
import com.jardessouza.desafio.pessoa.builder.PessoaDTOBuilder;
import com.jardessouza.desafio.pessoa.repository.PessoaRepository;
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
    private PessoaDTOBuilder pessoaDTOBuilder;
    @InjectMocks
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository pessoaRepositoryMock;

    @BeforeEach
    void setUp(){
        pessoaDTOBuilder = PessoaDTOBuilder.builder().build();

        BDDMockito.when(this.pessoaRepositoryMock.save(ArgumentMatchers.any(Pessoa.class)))
                        .thenReturn(pessoaDTOBuilder.criarPessoa());

        BDDMockito.when(this.pessoaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(pessoaDTOBuilder.criarPessoa()));

        BDDMockito.when(this.pessoaRepositoryMock.findByNome(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(pessoaDTOBuilder.criarPessoa()));

        BDDMockito.when(this.pessoaRepositoryMock.findAll())
                .thenReturn(List.of(pessoaDTOBuilder.criarPessoa()));

    }

    @Test
    void QuandoSalvarEobterSucessoRetornaPessoa(){
        BDDMockito.when(this.pessoaRepositoryMock.findByNome(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        PessoaRequestDTO pessoaEsperada = pessoaDTOBuilder.construirPessoaDTO();

        PessoaResponseDTO pessoaCriada = this.pessoaService.salvarPessoa
                (PessoaMapper.INSTANCE.toDTORequest(pessoaDTOBuilder.criarPessoa()));

        Assertions.assertThat(pessoaCriada.getId()).isNotNull();
        Assertions.assertThat(pessoaEsperada.getNome()).isEqualTo(pessoaEsperada.getNome());
    }

    @Test
    void QuandoSalvarEexistirNomeRetornarExcessao(){
        Assertions.assertThatExceptionOfType(PessoaJaExisteException.class)
                .isThrownBy(() -> this.pessoaService
                        .salvarPessoa(PessoaMapper.INSTANCE.toDTORequest(pessoaDTOBuilder.criarPessoa())));
    }
    @Test
    void QuandoObterSucessoAtualizarPessoa(){
        Assertions.assertThatCode(() -> this.pessoaService
                        .editarPessoa(1L, pessoaDTOBuilder.construirPessoaDTO()))
                        .doesNotThrowAnyException();
    }

    @Test
    void QuandoBuscarIdENaoObterSucessoRetornarExcessao(){
        BDDMockito.when(this.pessoaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> this.pessoaService.localizarEobterPessoa(1L));
    }

    @Test
    void QuandoBuscarNomeRetornarPessoaComSucesso(){
        Assertions.assertThatCode(() -> this.pessoaService
                .localizarEobterPessoa("Jardes Souza"))
                .doesNotThrowAnyException();
    }

    @Test
    void QuandoNaoEncontrarNomeRetornarExcessao(){
        BDDMockito.when(this.pessoaRepositoryMock.findByNome(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(PessoaNaoEncontradaException.class)
                .isThrownBy(() -> this.pessoaService.localizarEobterPessoa("Jardes Souza"));
    }

    @Test
    void QuantoObterSucessoRetornaListaPessoas(){
        String nomeEsperado = pessoaDTOBuilder.construirPessoaDTO().getNome();
        List<PessoaResponseDTO> listarPessoas = this.pessoaService.listarPessoas();

        Assertions.assertThat(listarPessoas)
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(listarPessoas.get(0).getNome()).isEqualTo(nomeEsperado);
    }


}
