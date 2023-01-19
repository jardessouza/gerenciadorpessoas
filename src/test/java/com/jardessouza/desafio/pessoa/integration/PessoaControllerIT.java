package com.jardessouza.desafio.pessoa.integration;

import com.jardessouza.desafio.pessoa.builder.PessoaDTOBuilder;
import com.jardessouza.desafio.pessoa.dto.MessageDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaRequestDTO;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import com.jardessouza.desafio.pessoa.mapper.PessoaMapper;
import com.jardessouza.desafio.pessoa.repository.PessoaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PessoaControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void QuandoSalvarRetornaPessoaComSucesso(){

        PessoaRequestDTO pessoaRequestDTO = PessoaDTOBuilder.builder().build().construirPessoaDTO();

        ResponseEntity<Pessoa> pessoaResponseEntity =
                testRestTemplate.postForEntity("/v1/gerenciarpessoas/pessoa", pessoaRequestDTO, Pessoa.class);

        Assertions.assertThat(pessoaResponseEntity).isNotNull();
        Assertions.assertThat(pessoaResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(pessoaResponseEntity.getBody()).isNotNull();
    }

    @Test
    void QuandoObterSucessoEditaPessoa(){
        Pessoa salvarPessoa = this.pessoaRepository.save(PessoaDTOBuilder.builder().build().criarPessoa());
        salvarPessoa.setNome("new name");

        PessoaRequestDTO pessoaRequestDTO = PessoaMapper.INSTANCE.toDTORequest(salvarPessoa);

        ResponseEntity<Void> pessoaResponseEntity = testRestTemplate.exchange(
                "/v1/gerenciarpessoas/pessoa/{id}", HttpMethod.PUT,
                new HttpEntity<>(pessoaRequestDTO), Void.class, 1L);

        Assertions.assertThat(pessoaResponseEntity).isNotNull();
        Assertions.assertThat(pessoaResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void QuandoLocalizarPessoaRetornaMensagemComDadosPessoa(){
        Pessoa salvarPessoa = this.pessoaRepository.save(PessoaDTOBuilder.builder().build().criarPessoa());

        MessageDTO pessoaMsg =
                testRestTemplate.getForObject("/v1/gerenciarpessoas/pessoa/{id}", MessageDTO.class, 1L);

        Assertions.assertThat(pessoaMsg).isNotNull();
    }

    @Test
    void QuandoObterSucessoRetornaListaDeTodasAsPessoas(){
        Pessoa salvarPessoa = this.pessoaRepository.save(PessoaDTOBuilder.builder().build().criarPessoa());

        String nomeEsperado = salvarPessoa.getNome();

        List<Pessoa> listaPessoas = testRestTemplate.exchange("/v1/gerenciarpessoas/pessoa", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Pessoa>>() {}).getBody();

        Assertions.assertThat(listaPessoas)
                .isNotNull();
        Assertions.assertThat(listaPessoas.get(0).getNome()).isEqualTo(nomeEsperado);
    }


}
