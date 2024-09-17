package com.leodelmiro.estabelecimento.core.domain;

import java.time.LocalDateTime;

public class Imagem {
    private Long id;
    private String nome;
    private String url;
    private LocalDateTime criadoEm;

    public Imagem() {
    }

    public Imagem(Long id, String nome, String url, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.url = url;
        this.criadoEm = (criadoEm == null) ? LocalDateTime.now() : criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = (criadoEm == null) ? LocalDateTime.now() : criadoEm;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
