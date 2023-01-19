package com.jardessouza.desafio.endereco.integration;

import com.jardessouza.desafio.endereco.builder.EnderecoDTOBuilder;
import com.jardessouza.desafio.endereco.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.endereco.entity.Endereco;
import com.jardessouza.desafio.endereco.mapper.EnderecoMapper;
import com.jardessouza.desafio.endereco.repository.EnderecoRepository;
import com.jardessouza.desafio.pessoa.builder.PessoaDTOBuilder;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import com.jardessouza.desafio.pessoa.repository.PessoaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EnderecoControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void QuandoSalvarRetornarEnderecoComSucesso(){
        Pessoa salvarPessoa = this.pessoaRepository.save(PessoaDTOBuilder.builder().build().criarPessoa());

        EnderecoRequestDTO enderecoRequestDTO =
                EnderecoMapper.INSTANCE.toDTORequest(EnderecoDTOBuilder.builder().build().criarEnderecoSemIdEPessoa());

        ResponseEntity<Endereco> enderecoResponseEntity = testRestTemplate.postForEntity(
                "/v1/gerenciarpessoas/endereco/{nomePessoa}", enderecoRequestDTO,
                Endereco.class, salvarPessoa.getNome());

        Assertions.assertThat(enderecoResponseEntity).isNotNull();
        Assertions.assertThat(enderecoResponseEntity.getBody().getCep()).isEqualTo(enderecoRequestDTO.getCep());
        Assertions.assertThat(enderecoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

}
