package com.jardessouza.desafio.mapper;

import com.jardessouza.desafio.dto.PessoaRequestDTO;
import com.jardessouza.desafio.dto.PessoaResponseDTO;
import com.jardessouza.desafio.entity.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    public static final PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    Pessoa toModel(PessoaRequestDTO pessoaRequestDTO);
    PessoaResponseDTO toDTO(Pessoa pessoa);
    PessoaResponseDTO toDTO(PessoaRequestDTO pessoaRequestDTO);

}
