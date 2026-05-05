package com.simplebank.api.config;

import com.simplebank.api.model.Conta;
import com.simplebank.api.repository.ContaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner carregarDadosIniciais(ContaRepository repository) {
        return args -> {
            // Só cria a conta se o banco estiver vazio
            if (repository.count() == 0) {
                Conta conta = new Conta();
                conta.setTitular("Venícius Rocha Dobler");
                conta.setSaldo(new BigDecimal("1000.00")); // Um saldo inicial de mil reais para testarmos saques
                repository.save(conta);

                System.out.println("Conta padrão criada com sucesso! ID: " + conta.getId());
            }
        };
    }
}