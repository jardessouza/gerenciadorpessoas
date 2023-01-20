package com.jardessouza.desafio.pessoa.util;

import com.jardessouza.desafio.pessoa.dto.MessageDTO;

import java.time.LocalDate;

public class MessageDTOUtils {
    public static MessageDTO retornarMensagemComDadosDaPessoa(Long id, String nome, LocalDate data){
        return retornarDadosPessoa(id, nome, String.valueOf(data));
    }

    private static MessageDTO retornarDadosPessoa(Long id, String nome, String data){

        String criarMensagem = String.format("id: %d , nome: %s , data de nascimento: %s", id, nome, data);

        return MessageDTO.builder()
                .message(criarMensagem)
                .build();
    }
}
