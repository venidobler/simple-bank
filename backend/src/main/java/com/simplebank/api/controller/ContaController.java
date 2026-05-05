package com.simplebank.api.controller;

import com.simplebank.api.controller.dto.TransacaoRequest;
import com.simplebank.api.model.Conta;
import com.simplebank.api.model.Transacao;
import com.simplebank.api.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe expõe endpoints REST e converte os retornos para JSON
@RequestMapping("/api/contas") // URL base para todos os métodos abaixo
@CrossOrigin(origins = "*") // Libera o CORS para o frontend (Vite) conectar sem erros
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    @GetMapping("/{id}")
    public ResponseEntity<Conta> obterConta(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.obterConta(id));
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<List<Transacao>> obterExtrato(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.obterExtrato(id));
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestBody TransacaoRequest request) {
        return ResponseEntity.ok(contaService.depositar(id, request.valor()));
    }

    @PostMapping("/{id}/sacar")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestBody TransacaoRequest request) {
        return ResponseEntity.ok(contaService.sacar(id, request.valor()));
    }
}