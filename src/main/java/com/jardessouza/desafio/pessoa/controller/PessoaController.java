package com.jardessouza.desafio.pessoa.controller;

import com.jardessouza.desafio.pessoa.dto.MessageDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaRequestDTO;
import com.jardessouza.desafio.pessoa.dto.PessoaResponseDTO;
import com.jardessouza.desafio.pessoa.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/gerenciarpessoas/pessoa")
@RequiredArgsConstructor
public class PessoaController implements PessoaControllerDocs {
    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponseDTO> salvarPessoa(@RequestBody @Valid PessoaRequestDTO pessoaRequestDTO){
        return new ResponseEntity<>(this.pessoaService.salvarPessoa(pessoaRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{pessoaId}")
    public ResponseEntity<Void> editarPessoa(@PathVariable Long pessoaId,
                                             @RequestBody PessoaRequestDTO pessoaRequestDTO){
        this.pessoaService.editarPessoa(pessoaId, pessoaRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{pessoaId}")
    public ResponseEntity<MessageDTO> consultarPessoa(@PathVariable Long pessoaId){
        return ResponseEntity.ok(this.pessoaService.consutarUmaPessoa(pessoaId));
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDTO>> listarPessoas(){
        return ResponseEntity.ok(this.pessoaService.listarPessoas());
    }




}
