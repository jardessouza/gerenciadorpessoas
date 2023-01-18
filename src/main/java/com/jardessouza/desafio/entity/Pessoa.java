package com.jardessouza.desafio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_PESSOAS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 155)
    private String nome;

    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<Endereco> endereco;
}
