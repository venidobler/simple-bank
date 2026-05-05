package com.simplebank.api.service;

import com.simplebank.api.model.Conta;
import com.simplebank.api.model.TipoTransacao;
import com.simplebank.api.model.Transacao;
import com.simplebank.api.repository.ContaRepository;
import com.simplebank.api.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service // Diz ao Spring que esta classe contém regras de negócio
@RequiredArgsConstructor // Lombok: Cria o construtor automaticamente para injetar os repositórios
public class ContaService {

    // A injeção de dependências: o Spring entrega os repositórios prontos para uso
    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public Conta obterConta(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public List<Transacao> obterExtrato(Long contaId) {
        return transacaoRepository.findByContaId(contaId);
    }

    // @Transactional é mágico: se der erro na metade do código (ex: salvou a transação mas
    // falhou ao atualizar o saldo), ele faz "rollback" e desfaz tudo no banco de dados.
    @Transactional
    public Conta depositar(Long contaId, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero");
        }

        Conta conta = obterConta(contaId);

        // Atualiza o saldo
        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);

        // Registra o histórico
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(valor);
        transacao.setTipo(TipoTransacao.DEPOSITO);
        transacao.setDataHora(LocalDateTime.now());
        transacaoRepository.save(transacao);

        return conta;
    }

    @Transactional
    public Conta sacar(Long contaId, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero");
        }

        Conta conta = obterConta(contaId);

        // A regra de negócio principal!
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        // Atualiza o saldo
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);

        // Registra o histórico
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(valor);
        transacao.setTipo(TipoTransacao.SAQUE);
        transacao.setDataHora(LocalDateTime.now());
        transacaoRepository.save(transacao);

        return conta;
    }
}