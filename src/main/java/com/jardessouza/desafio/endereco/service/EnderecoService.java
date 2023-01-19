package com.jardessouza.desafio.endereco.service;

import com.jardessouza.desafio.endereco.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.endereco.dto.EnderecoResponseDTO;
import com.jardessouza.desafio.endereco.repository.EnderecoRepository;
import com.jardessouza.desafio.endereco.entity.Endereco;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import com.jardessouza.desafio.endereco.mapper.EnderecoMapper;
import com.jardessouza.desafio.pessoa.service.PessoaService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaService pessoaService) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaService = pessoaService;
    }

   public EnderecoResponseDTO create(String nome, EnderecoRequestDTO enderecoRequestDTO){
       Endereco enderecoParaSalvar = obterPessoaEcriarEndereco(nome, enderecoRequestDTO);

       Endereco enderecoSalvo = this.enderecoRepository.save(enderecoParaSalvar);

       return EnderecoMapper.INSTANCE.toDTO(enderecoSalvo);
   }

    public List<EnderecoResponseDTO> listarEnderecosDaPessoa(String nomePessoa){
       Pessoa pessoaEncontrada = this.pessoaService.localizarEobterPessoa(nomePessoa);

       return obterTodosEnderecosDePessoa(pessoaEncontrada);
   }

    private List<EnderecoResponseDTO> obterTodosEnderecosDePessoa(Pessoa pessoaEncontrada) {
        return this.enderecoRepository.findAllByPessoa(pessoaEncontrada).stream()
                .map(EnderecoMapper.INSTANCE::toDTO)
                .sorted(Comparator.comparing(EnderecoResponseDTO::getPrioridadeEndereco))
                .collect(Collectors.toList());
    }

    private Endereco obterPessoaEcriarEndereco(String nome, EnderecoRequestDTO enderecoRequestDTO) {
        Pessoa pessoaEncontrada = this.pessoaService.localizarEobterPessoa(nome);
        Endereco enderecoParaSalvar = EnderecoMapper.INSTANCE.toModel(enderecoRequestDTO);
        enderecoParaSalvar.setPessoa(pessoaEncontrada);
        return enderecoParaSalvar;
    }

}
