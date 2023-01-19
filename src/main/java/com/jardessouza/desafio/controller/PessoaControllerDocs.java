package com.jardessouza.desafio.controller;

import com.jardessouza.desafio.dto.PessoaRequestDTO;
import com.jardessouza.desafio.dto.PessoaResponseDTO;
import com.jardessouza.desafio.entity.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PessoaControllerDocs {
    @Operation(summary = "Operacao de criacao de Pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatorios ausentes" )
    })
    ResponseEntity<PessoaResponseDTO> salvarPessoa(PessoaRequestDTO pessoaRequestDTO);
    @Operation(summary = "Operacao para editar Pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa editada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatorios ausentes")
    })
    ResponseEntity<Void> editarPessoa(Long pessoaId, PessoaRequestDTO pessoaRequestDTO);

    @Operation(summary = "Operacao para consultar pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa nao encontrada")
    })
    ResponseEntity<Pessoa> consultarUmaPessoa(Long pessoaId);

    @Operation(summary = "Operacao para Listar Pessoas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso")
    })
    ResponseEntity<List<PessoaResponseDTO>> listarPessoas();

}
