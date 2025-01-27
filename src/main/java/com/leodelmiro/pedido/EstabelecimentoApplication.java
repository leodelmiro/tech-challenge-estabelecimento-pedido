package com.leodelmiro.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EstabelecimentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstabelecimentoApplication.class, args);
    }
    /* TODO ADICIONAR COBERTURA DE TESTES 80% NA PIPELINE PARA DEV E INTEGRAÇÃO RODANDO PÓS DEV, GITHUB ACTIONS E INFRA BANCO, DOCUMENTAÇÃO, COLOCAR URL NLB*/

}
