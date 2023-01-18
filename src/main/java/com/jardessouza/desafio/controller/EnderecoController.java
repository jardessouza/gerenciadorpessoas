package com.jardessouza.desafio.controller;

import com.jardessouza.desafio.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.dto.EnderecoResponseDTO;
import com.jardessouza.desafio.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/gerenciarpessoas/endereco")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;

    @PostMapping(path = "/{nomePessoa}")
    public ResponseEntity<EnderecoResponseDTO> criarEnderecoPessoa(
            @PathVariable String nomePessoa,
            @RequestBody @Valid EnderecoRequestDTO enderecoRequestDTO){
        return new ResponseEntity<>(this.enderecoService
                .create(nomePessoa,enderecoRequestDTO), HttpStatus.CREATED);
    }



}