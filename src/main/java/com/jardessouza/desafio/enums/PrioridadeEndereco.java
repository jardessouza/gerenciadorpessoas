package com.jardessouza.desafio.enums;

import lombok.Getter;

@Getter
public enum PrioridadeEndereco {
    ENDERECO_PRINCIPAL(0,"Endereco Principal"),
    ENDERECO_ALTERNATIVO(1,"Endereco alternativo");

    private final String descricao;

    PrioridadeEndereco(int num,String descricao){

        this.descricao = descricao;
    }
}
