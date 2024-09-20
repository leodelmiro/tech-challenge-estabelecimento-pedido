package com.leodelmiro.estabelecimento.core.domain;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CPF {
    private String cpf;

    public CPF() {
    }

    public CPF(String cpf) {
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
