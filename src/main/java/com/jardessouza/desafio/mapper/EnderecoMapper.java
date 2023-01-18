package com.jardessouza.desafio.mapper;

import com.jardessouza.desafio.dto.EnderecoRequestDTO;
import com.jardessouza.desafio.dto.EnderecoResponseDTO;
import com.jardessouza.desafio.entity.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    public static final EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    Endereco toModel(EnderecoRequestDTO enderecoDTO);
    EnderecoResponseDTO toDTO(Endereco endereco);
}
