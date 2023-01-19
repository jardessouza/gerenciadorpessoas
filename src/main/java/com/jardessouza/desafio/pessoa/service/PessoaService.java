package com.jardessouza.desafio.pessoa.service;

import com.jardessouza.desafio.pessoa.dto.MessageDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaRequestDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaResponseDTO;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import com.jardessouza.desafio.pessoa.exception.PessoaJaExisteException;
import com.jardessouza.desafio.pessoa.exception.PessoaNaoEncontradaException;
import com.jardessouza.desafio.pessoa.mapper.PessoaMapper;
import com.jardessouza.desafio.pessoa.repository.PessoaRepository;
import com.jardessouza.desafio.pessoa.util.MessageDTOUtils;
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
        Pessoa pessoaCriada = verificaSeExisteEsalvaPessoa(pessoaRequestDTO);
        return PessoaMapper.INSTANCE.toDTO(pessoaCriada);
    }

    public void editarPessoa(Long pessoaId, PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoaEncontrada = localizarEobterPessoa(pessoaId);
        pessoaRequestDTO.setId(pessoaEncontrada.getId());
        this.pessoaRepository.save(PessoaMapper.INSTANCE.toModel(pessoaRequestDTO));
    }

    public List<PessoaResponseDTO> listarPessoas() {
        return this.pessoaRepository.findAll()
                .stream().map(PessoaMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public MessageDTO consutarUmaPessoa(Long pessoaId){
        PessoaResponseDTO pessoaEncontrada = PessoaMapper.INSTANCE.toDTO(localizarEobterPessoa(pessoaId));
        return MessageDTOUtils.retornarMensagemComDadosDaPessoa
                (pessoaEncontrada.getId(), pessoaEncontrada.getNome(), pessoaEncontrada.getDataNascimento());
    }

    private Pessoa verificaSeExisteEsalvaPessoa(PessoaRequestDTO pessoaRequestDTO) {
        verificarSeNomeExiste(pessoaRequestDTO.getNome());
        Pessoa pessoaCriada = PessoaMapper.INSTANCE.toModel(pessoaRequestDTO);
        this.pessoaRepository.save(pessoaCriada);
        return pessoaCriada;
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
