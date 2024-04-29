package com.example.senior.dto;

import com.example.senior.enumeration.ETipoMensagem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MensagemValidacao implements Serializable {

    private ETipoMensagem tipo;
    private String codigo;
    private String titulo;
    private String descricao;
    private String data;

    public MensagemValidacao(Throwable ex) {
        this.tipo = ETipoMensagem.ERROR;
        this.codigo = "";
        this.titulo = ex.getMessage();
        this.descricao = ex.getMessage();
    }

    public MensagemValidacao(ETipoMensagem tipo, String codigo, String titulo, String descricao) {
        super();
        this.tipo = tipo;
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public MensagemValidacao(ETipoMensagem tipo, String codigo, String titulo, String descricao, String data) {
        super();
        this.tipo = tipo;
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }

    public MensagemValidacao(ETipoMensagem tipo, String titulo) {
        super();
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public MensagemValidacao(String titulo) {
        super();
        this.tipo = ETipoMensagem.ERROR;
        this.titulo = titulo;
    }

    public MensagemValidacao(ETipoMensagem tipo, String titulo, String codigo) {
        super();
        this.tipo = tipo;
        this.titulo = titulo;
        this.codigo = codigo;
    }
}
