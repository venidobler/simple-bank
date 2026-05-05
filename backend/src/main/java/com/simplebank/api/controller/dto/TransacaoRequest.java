package com.simplebank.api.controller.dto;

import java.math.BigDecimal;

// Um Record no Java 21 é perfeito para DTOs.
// Ele cria uma estrutura imutável para transportar dados sem precisar de Getters/Setters.
public record TransacaoRequest(BigDecimal valor) {
}