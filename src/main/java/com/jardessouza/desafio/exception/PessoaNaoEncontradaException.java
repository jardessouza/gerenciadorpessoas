package com.jardessouza.desafio.exception;

import javax.persistence.EntityNotFoundException;

public class PessoaNaoEncontradaException extends EntityNotFoundException {
    public PessoaNaoEncontradaException(String pessoa){
        super(String.format("Pessoa com nome %s nao encontrada!", pessoa));
    }
    public PessoaNaoEncontradaException(Long id){
        super(String.format("Pessoa com id %d nao encontrada!", id));
    }
}
