package com.example.senior.service;

import com.example.senior.dto.MensagemValidacao;
import com.example.senior.enumeration.ETipoMensagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MensagemValidacaoException extends RuntimeException {

    private final List<MensagemValidacao> validacoes = new ArrayList<>();

    public MensagemValidacaoException(List<MensagemValidacao> mensagens) {
        this.validacoes.addAll(mensagens);
    }

    public MensagemValidacaoException(MensagemValidacao mensagemValidacao) {
        this.validacoes.add(mensagemValidacao);
    }

    public MensagemValidacaoException(String mensagem) {
        this.validacoes.add(new MensagemValidacao(ETipoMensagem.ERROR, mensagem));
    }

    public MensagemValidacao getMensagemValidacao() {
        if (!validacoes.isEmpty()) {
            return validacoes.get(0);
        }
        return null;
    }

    public List<MensagemValidacao> getMensagens() {
        return validacoes;
    }

    public boolean isMultipleMessages() {
        return validacoes.size() > 1;
    }

    @Override
    public String getMessage() {
        return Optional.ofNullable(getMensagemValidacao()).map(MensagemValidacao::getTitulo).orElse(null);
    }
}
