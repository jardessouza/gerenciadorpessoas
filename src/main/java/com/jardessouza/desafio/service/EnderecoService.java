package com.jardessouza.desafio.service;

import com.jardessouza.desafio.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.dto.EnderecoResponseDTO;
import com.jardessouza.desafio.entity.Endereco;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.mapper.EnderecoMapper;
import com.jardessouza.desafio.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;

    public EnderecoResponseDTO criarEnderecoPessoa(Long pessoaId, EnderecoRequestDTO enderecoRequestDTO) {
        Pessoa encontrarPessoa = this.pessoaService.verificarSePessoaExiste(pessoaId);
        Endereco enderecoParaSalvar = EnderecoMapper.INSTANCE.toModel(enderecoRequestDTO);
        Endereco enderecoSalvo = this.enderecoRepository.save(enderecoParaSalvar);
        enderecoSalvo.setPessoa(encontrarPessoa);
        return EnderecoMapper.INSTANCE.toDTO(enderecoSalvo);
    }
}
