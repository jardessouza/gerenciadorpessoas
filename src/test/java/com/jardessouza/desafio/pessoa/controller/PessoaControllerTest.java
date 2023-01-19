package com.jardessouza.desafio.pessoa.controller;

import com.jardessouza.desafio.controller.PessoaController;
import com.jardessouza.desafio.dto.PessoaRequestDTO;
import com.jardessouza.desafio.dto.PessoaResponseDTO;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.mapper.PessoaMapper;
import com.jardessouza.desafio.pessoa.builder.PessoaBuilderDTO;
import com.jardessouza.desafio.service.PessoaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class PessoaControllerTest {
    private PessoaBuilderDTO pessoaBuilderDTO;
    @InjectMocks
    private PessoaController pessoaController;
    @Mock
    private PessoaService pessoaServiceMock;

    @BeforeEach
    void setUp() {
        pessoaBuilderDTO = PessoaBuilderDTO.builder().build();

        BDDMockito.when(this.pessoaServiceMock.listarPessoas())
                .thenReturn(List.of(PessoaMapper.INSTANCE.toDTO(pessoaBuilderDTO.criarPessoa())));

        BDDMockito.when(this.pessoaServiceMock.salvarPessoa(ArgumentMatchers.any(PessoaRequestDTO.class)))
                .thenReturn(PessoaMapper.INSTANCE.toDTO(pessoaBuilderDTO.criarPessoa()));

        BDDMockito.when(this.pessoaServiceMock.verificarSePessoaExiste(ArgumentMatchers.anyLong()))
                .thenReturn(pessoaBuilderDTO.criarPessoa());
    }

    @Test
    void QuandoObterSucessoRetornaListaPessoas() {
        String nomeEsperado = pessoaBuilderDTO.construirPessoaDTO().getNome();
        List<PessoaResponseDTO> listaPessoas = this.pessoaController.listarPessoas().getBody();

        Assertions.assertThat(listaPessoas)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(listaPessoas.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    void QuandoObterSucessoSalvarERetornarPessoa() {
        PessoaRequestDTO pessoaParaSalvar = pessoaBuilderDTO.construirPessoaDTO();
        PessoaResponseDTO pessoaCriada = this.pessoaController.salvarPessoa(pessoaParaSalvar).getBody();

        Assertions.assertThat(pessoaCriada.getId())
                .isNotNull()
                .isEqualTo(pessoaParaSalvar.getId());
    }

    @Test
    void QuandoObterSucessoAtualizarPessoa() {
        Assertions.assertThatCode(() -> this.pessoaController
                        .editarPessoa(1L, pessoaBuilderDTO.construirPessoaDTO()))
                .doesNotThrowAnyException();
        ResponseEntity<Void> pessoa = this.pessoaController
                .editarPessoa(1L, pessoaBuilderDTO.construirPessoaDTO());

        Assertions.assertThat(pessoa).isNotNull();
        Assertions.assertThat(pessoa.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void QuandoEncontrarIdRetornarPessoaComSucesso() {
        Long idEsperado = pessoaBuilderDTO.construirPessoaDTO().getId();
        Pessoa pessoaEncontrada = this.pessoaController.consultarUmaPessoa(1L).getBody();
        Assertions.assertThat(pessoaEncontrada).isNotNull();
        Assertions.assertThat(pessoaEncontrada.getId()).isEqualTo(idEsperado);
    }

}
