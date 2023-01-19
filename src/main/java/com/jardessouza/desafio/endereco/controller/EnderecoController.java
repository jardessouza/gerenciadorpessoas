package com.jardessouza.desafio.endereco.controller;

import com.jardessouza.desafio.endereco.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.endereco.dto.EnderecoResponseDTO;
import com.jardessouza.desafio.endereco.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/gerenciarpessoas/endereco")
@RequiredArgsConstructor
public class EnderecoController implements EnderecoControllerDocs {
    private final EnderecoService enderecoService;

    @PostMapping(path = "/{nomePessoa}")
    public ResponseEntity<EnderecoResponseDTO> criarEnderecoPessoa(
            @PathVariable String nomePessoa,
            @RequestBody @Valid EnderecoRequestDTO enderecoRequestDTO){
        return new ResponseEntity<>(this.enderecoService
                .create(nomePessoa,enderecoRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{nomePessoa}")
    public ResponseEntity<List<EnderecoResponseDTO>> listarEnderecosPessoa(@PathVariable String nomePessoa){
        return ResponseEntity.ok(this.enderecoService.listarEnderecosDaPessoa(nomePessoa));
    }



}
