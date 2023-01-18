package com.jardessouza.desafio.service;

import com.jardessouza.desafio.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.dto.EnderecoResponseDTO;
import com.jardessouza.desafio.entity.Endereco;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.mapper.EnderecoMapper;
import com.jardessouza.desafio.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaService pessoaService) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaService = pessoaService;
    }

   public EnderecoResponseDTO create(String nome, EnderecoRequestDTO enderecoRequestDTO){
       Pessoa pessoaEncontrada = this.pessoaService.verificarSePessoaExiste(nome);
       Endereco enderecoParaSalvar = EnderecoMapper.INSTANCE.toModel(enderecoRequestDTO);
       enderecoParaSalvar.setPessoa(pessoaEncontrada);

       Endereco enderecoSalvo = this.enderecoRepository.save(enderecoParaSalvar);

       return EnderecoMapper.INSTANCE.toDTO(enderecoSalvo);
   }

}
