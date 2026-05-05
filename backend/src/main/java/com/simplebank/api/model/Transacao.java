package com.simplebank.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING) // Salva no banco como texto ("SAQUE") e não como número (0 ou 1)
    private TipoTransacao tipo;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "conta_id") // Cria a coluna de chave estrangeira
    private Conta conta;
}