package com.jardessouza.desafio.service;

import com.jardessouza.desafio.dto.PessoaRequestDTO;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.mapper.PessoaMapper;
import com.jardessouza.desafio.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaRequestDTO salvarPessoa(PessoaRequestDTO pessoaDTO){
        Pessoa pessoaCriada = PessoaMapper.INSTANCE.toModel(pessoaDTO);
        this.pessoaRepository.save(pessoaCriada);
        return pessoaDTO;
    }

    public void editarPessoa(Long pessoaId, PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoaEncontrada = verificarSePessoaExiste(pessoaId);;
        pessoaRequestDTO.setId(pessoaEncontrada.getId());
        this.pessoaRepository.save(PessoaMapper.INSTANCE.toModel(pessoaRequestDTO));
    }

    public List<PessoaRequestDTO> listarPessoas(){
        return this.pessoaRepository.findAll()
                .stream().map(PessoaMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Pessoa verificarSePessoaExiste(Long id){
        return this.pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa nao encontrada"));
    }

    public void verificarSePessoaExiste(String nome){
        this.pessoaRepository.findByNome(nome)
                .ifPresent((pessoa -> {throw new EntityNotFoundException("Pessoa nao encontrada");}));
    }

}
