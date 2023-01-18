package com.jardessouza.desafio.controller;

import com.jardessouza.desafio.dto.PessoaDTO;
import com.jardessouza.desafio.dto.PessoaRequestDTO;
import com.jardessouza.desafio.dto.PessoaResponseDTO;
import com.jardessouza.desafio.entity.Pessoa;
import com.jardessouza.desafio.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/gerenciarpessoas/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;


    @PostMapping
    public ResponseEntity<PessoaRequestDTO> salvarPessoa(@RequestBody @Valid PessoaRequestDTO pessoaDTO){
        return new ResponseEntity<>(this.pessoaService.salvarPessoa(pessoaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> editarPessoa(@RequestBody PessoaDTO pessoaDTO){
        this.pessoaService.editarPessoa(pessoaDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{pessoaId}")
    public ResponseEntity<Pessoa> consultarUmaPessoa(@PathVariable Long pessoaId){
        return ResponseEntity.ok(this.pessoaService.verificarSePessoaExiste(pessoaId));
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDTO>> listarPessoas(){
        return ResponseEntity.ok(this.pessoaService.listarPessoas());
    }




}
