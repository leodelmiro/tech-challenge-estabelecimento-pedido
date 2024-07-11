package com.leodelmiro.estabelecimento.adapters.out.repository.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @Nullable private String nome;

    @Nullable private String email;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, String cpf, String nome, String email, LocalDateTime criadoEm) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.criadoEm = criadoEm;
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

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
