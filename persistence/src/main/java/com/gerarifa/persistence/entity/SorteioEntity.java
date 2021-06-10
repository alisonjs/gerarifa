package com.gerarifa.persistence.entity;

import com.gerarifa.domain.model.status.StatusSorteio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sorteio")
public class SorteioEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nome;

    @Column(columnDefinition="TEXT")
    private String descricao;

    @Column(name = "preco_bilhete")
    private Double precoBilhete;

    @Column(name = "meta_arrecadacao")
    private Double metaArrecadacao;

    @Column(name = "data_criacao", columnDefinition = "DATE")
    private LocalDate dataCriacao;

    @Column(name = "data_inicio", columnDefinition = "DATE")
    private LocalDate dataInicio;

    @Column(name = "data_realizacao", columnDefinition = "DATE")
    private LocalDate dataRealizacaoSorteio;

    private String premio;

    @Enumerated(EnumType.STRING)
    private StatusSorteio status;
}
