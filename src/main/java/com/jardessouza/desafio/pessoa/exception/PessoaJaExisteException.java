package com.jardessouza.desafio.pessoa.exception;

import javax.persistence.EntityExistsException;

public class PessoaJaExisteException extends EntityExistsException {
    public PessoaJaExisteException(String nome){
        super(String.format("Pessoa com nome %s ja existe!", nome));
    }
}
