package com.simplebank.api.repository;

import com.simplebank.api.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    // O Spring é tão inteligente que, se você escrever o nome do método em inglês
    // seguindo os atributos da classe, ele cria o SQL de busca automaticamente!
    // Aqui estamos criando uma busca de transações baseada no ID da conta.
    List<Transacao> findByContaId(Long contaId);

}