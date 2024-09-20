package com.leodelmiro.estabelecimento.dataprovider.repository.entity;

import jakarta.persistence.Embeddable;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

@Embeddable
public class CPFEntity {
    private String cpf;

    public CPFEntity() {
    }

    public CPFEntity(String cpf) {
        if (!isValid(cpf)) throw new IllegalArgumentException("Esse CPF é inválido");
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean isValid(String cpf) {
        var validator = new CPFValidator();
        return cpf.matches("^[0-9]{11}$") && validator.isValid(cpf, null);
    }
}
