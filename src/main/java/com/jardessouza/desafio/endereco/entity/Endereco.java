package com.jardessouza.desafio.endereco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jardessouza.desafio.endereco.enums.PrioridadeEndereco;
import com.jardessouza.desafio.pessoa.entity.Pessoa;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TB_ENDERECOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Endereco implements Comparable<Endereco> {
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

    @Override
    public int compareTo(Endereco o) {
        return prioridadeEndereco.compareTo(o.getPrioridadeEndereco());
    }
}
