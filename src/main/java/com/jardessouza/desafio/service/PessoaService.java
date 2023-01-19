package com.jardessouza.desafio.service;

import com.jardessouza.desafio.dto.PessoaRequestDTO;
import com.jardessouza.desafio.dto.PessoaResponseDTO;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.exception.PessoaJaExisteException;
import com.jardessouza.desafio.exception.PessoaNaoEncontradaException;
import com.jardessouza.desafio.mapper.PessoaMapper;
import com.jardessouza.desafio.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaResponseDTO salvarPessoa(PessoaRequestDTO pessoaRequestDTO) {
        verificarSeNomeExiste(pessoaRequestDTO.getNome());
        Pessoa pessoaCriada = PessoaMapper.INSTANCE.toModel(pessoaRequestDTO);
        this.pessoaRepository.save(pessoaCriada);
        return PessoaMapper.INSTANCE.toDTO(pessoaCriada);
    }

    public void editarPessoa(Long pessoaId, PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoaEncontrada = localizarEobterPessoa(pessoaId);
        ;
        pessoaRequestDTO.setId(pessoaEncontrada.getId());
        this.pessoaRepository.save(PessoaMapper.INSTANCE.toModel(pessoaRequestDTO));
    }

    public List<PessoaResponseDTO> listarPessoas() {
        return this.pessoaRepository.findAll()
                .stream().map(PessoaMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Pessoa localizarEobterPessoa(Long id) {
        return this.pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException(id));
    }

    public Pessoa localizarEobterPessoa(String nome) {
        return this.pessoaRepository.findByNome(nome)
                .orElseThrow(() -> new PessoaNaoEncontradaException(nome));
    }

    public void verificarSeNomeExiste(String nome){
        this.pessoaRepository.findByNome(nome)
                .ifPresent(pessoa -> {throw new PessoaJaExisteException(nome);});
    }

}
