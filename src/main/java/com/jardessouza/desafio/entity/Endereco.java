package com.jardessouza.desafio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jardessouza.desafio.enums.PrioridadeEndereco;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TB_ENDERECOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String logadouro;

    @Column(nullable = false, length = 8)
    private Long cep;

    @Column(nullable = false, length = 10)
    private Long numero;

    @Column(nullable = false, length = 25)
    private String cidade;

    @Column(nullable = false)
    private PrioridadeEndereco prioridadeEndereco;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
