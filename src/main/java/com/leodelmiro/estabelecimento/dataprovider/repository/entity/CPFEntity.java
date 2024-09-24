package com.leodelmiro.estabelecimento.dataprovider.repository.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class CPFEntity {
    private String cpf;

    public CPFEntity() {
    }

    public CPFEntity(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
