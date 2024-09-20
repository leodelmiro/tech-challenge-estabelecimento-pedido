package com.leodelmiro.estabelecimento.dataprovider.repository.entity;

import com.leodelmiro.estabelecimento.core.domain.CPF;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CPFEntity cpf;

    private String nome;

    private String email;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, CPFEntity cpf, String nome, String email, LocalDateTime criadoEm) {
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

    public CPFEntity getCpf() {
        return cpf;
    }

    public void setCpf(CPFEntity cpf) {
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
