package com.jardessouza.desafio.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.SortedSet;

@Entity
@Table(name = "TB_PESSOAS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @SortComparator(ClasseComparator.class)
    private SortedSet<Endereco> endereco;

    public static class ClasseComparator implements Comparator<Endereco>{
        @Override
        public int compare(Endereco o1, Endereco o2) {
            return o1.getPrioridadeEndereco().compareTo(o2.getPrioridadeEndereco());
        }
    }
}
