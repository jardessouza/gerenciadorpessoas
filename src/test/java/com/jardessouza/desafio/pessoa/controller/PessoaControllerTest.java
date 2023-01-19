package com.jardessouza.desafio.pessoa.controller;

import com.jardessouza.desafio.pessoa.dto.MessageDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaRequestDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaResponseDTO;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import com.jardessouza.desafio.pessoa.mapper.PessoaMapper;
import com.jardessouza.desafio.pessoa.builder.PessoaDTOBuilder;
import com.jardessouza.desafio.pessoa.service.PessoaService;
import com.jardessouza.desafio.pessoa.util.MessageDTOUtils;
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

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class PessoaControllerTest {
    private PessoaDTOBuilder pessoaDTOBuilder;
    @InjectMocks
    private PessoaController pessoaController;
    @Mock
    private PessoaService pessoaServiceMock;

    @BeforeEach
    void setUp() {
        pessoaDTOBuilder = PessoaDTOBuilder.builder().build();

        BDDMockito.when(this.pessoaServiceMock.consutarUmaPessoa(ArgumentMatchers.anyLong()))
                        .thenReturn(MessageDTOUtils
                                .retornarMensagemComDadosDaPessoa(1L, "Jardes Souza", LocalDate.now()));

        BDDMockito.when(this.pessoaServiceMock.listarPessoas())
                .thenReturn(List.of(PessoaMapper.INSTANCE.toDTO(pessoaDTOBuilder.criarPessoa())));

        BDDMockito.when(this.pessoaServiceMock.salvarPessoa(ArgumentMatchers.any(PessoaRequestDTO.class)))
                .thenReturn(PessoaMapper.INSTANCE.toDTO(pessoaDTOBuilder.criarPessoa()));

        BDDMockito.when(this.pessoaServiceMock.localizarEobterPessoa(ArgumentMatchers.anyLong()))
                .thenReturn(pessoaDTOBuilder.criarPessoa());
    }

    @Test
    void QuandoObterSucessoRetornaListaPessoas() {
        String nomeEsperado = pessoaDTOBuilder.construirPessoaDTO().getNome();
        List<PessoaResponseDTO> listaPessoas = this.pessoaController.listarPessoas().getBody();

        Assertions.assertThat(listaPessoas)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(listaPessoas.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    void QuandoObterSucessoSalvarERetornarPessoa() {
        PessoaRequestDTO pessoaParaSalvar = pessoaDTOBuilder.construirPessoaDTO();
        PessoaResponseDTO pessoaCriada = this.pessoaController.salvarPessoa(pessoaParaSalvar).getBody();

        Assertions.assertThat(pessoaCriada.getId())
                .isNotNull()
                .isEqualTo(pessoaParaSalvar.getId());
    }

    @Test
    void QuandoObterSucessoAtualizarPessoa() {
        Assertions.assertThatCode(() -> this.pessoaController
                        .editarPessoa(1L, pessoaDTOBuilder.construirPessoaDTO()))
                .doesNotThrowAnyException();
        ResponseEntity<Void> pessoa = this.pessoaController
                .editarPessoa(1L, pessoaDTOBuilder.construirPessoaDTO());

        Assertions.assertThat(pessoa).isNotNull();
        Assertions.assertThat(pessoa.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void QuandoEncontrarIdRetornarPessoaComSucesso() {
        MessageDTO mensagemPessoa = this.pessoaController.consultarPessoa(1L).getBody();
        Assertions.assertThat(mensagemPessoa).isNotNull();
        Assertions.assertThat(mensagemPessoa.getMessage()).isNotEmpty();
    }

}
