package com.leodelmiro.estabelecimento.application.core.domain;

import java.time.LocalDateTime;

public class Cliente {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private final LocalDateTime criadoEm = LocalDateTime.now();

    public Cliente() {
    }

    public Cliente(Long id, String cpf, String nome, String email) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
