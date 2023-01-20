package com.jardessouza.desafio.endereco.controller;

import com.jardessouza.desafio.endereco.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.endereco.dto.EnderecoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnderecoControllerDocs {
    @Operation(summary = "Operacao de criar endereco para pessoa",
    description = "Criar endereco para pessoa indicando nome na URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereco criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatorios ausentes")
    })
    ResponseEntity<EnderecoResponseDTO> criarEnderecoPessoa(String nomePessoa, EnderecoRequestDTO enderecoRequestDTO);

    @Operation(summary = "Operacao para econtrar Enderecos por Pessoa",
    description = "Listar enderenco da pessoa digitando nome na URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de enderecos encontrada sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada")
    })
    ResponseEntity<List<EnderecoResponseDTO>> listarEnderecosPessoa(String nomePessoa);
}

