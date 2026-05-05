package com.simplebank.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity // Diz ao Spring que isso vai virar uma tabela no banco
@Table(name = "contas")
@Data // Lombok: Gera Getters, Setters, toString, etc.
@NoArgsConstructor // Lombok: Gera um construtor vazio (exigência do JPA)
@AllArgsConstructor // Lombok: Gera um construtor com todos os atributos
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento (1, 2, 3...)
    private Long id;

    private String titular;

    private BigDecimal saldo;
}