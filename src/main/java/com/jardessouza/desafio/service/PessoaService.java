package com.jardessouza.desafio.service;

import com.jardessouza.desafio.dto.PessoaRequestDTO;
import com.jardessouza.desafio.dto.PessoaResponseDTO;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.mapper.PessoaMapper;
import com.jardessouza.desafio.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaResponseDTO salvarPessoa(PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoaCriada = PessoaMapper.INSTANCE.toModel(pessoaRequestDTO);
        this.pessoaRepository.save(pessoaCriada);
        return PessoaMapper.INSTANCE.toDTO(pessoaCriada);
    }

    public void editarPessoa(Long pessoaId, PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoaEncontrada = verificarSePessoaExiste(pessoaId);;
        pessoaRequestDTO.setId(pessoaEncontrada.getId());
        this.pessoaRepository.save(PessoaMapper.INSTANCE.toModel(pessoaRequestDTO));
    }

    public List<PessoaResponseDTO> listarPessoas(){
        return this.pessoaRepository.findAll()
                .stream().map(PessoaMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Pessoa verificarSePessoaExiste(Long id){
        return this.pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa nao encontrada"));
    }

    public Pessoa verificarSePessoaExiste(String nome){
        return this.pessoaRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa nao encontrada"));
    }

}
