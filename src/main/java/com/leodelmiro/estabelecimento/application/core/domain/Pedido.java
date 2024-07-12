package com.leodelmiro.estabelecimento.application.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Long id;
    private Cliente cliente;
    private BigDecimal precoTotal;
    private StatusPedido status;
    private Long tempoTotalDePreparoEmSegundos;
    private LocalDateTime pagoEm;
    private LocalDateTime criadoEm;

    private List<Produto> produtos = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Cliente cliente, StatusPedido status, BigDecimal precoTotal, Long tempoTotalDePreparoEmSegundos) {
        this.cliente = cliente;
        this.status = status;
        this.precoTotal = precoTotal;
        this.tempoTotalDePreparoEmSegundos = tempoTotalDePreparoEmSegundos;
    }

    public Pedido(Long id, Cliente cliente, BigDecimal precoTotal, StatusPedido status, Long tempoTotalDePreparoEmSegundos, LocalDateTime criadoEm) {
        this.id = id;
        this.cliente = cliente;
        this.precoTotal = precoTotal;
        this.status = status;
        this.tempoTotalDePreparoEmSegundos = tempoTotalDePreparoEmSegundos;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Long getTempoTotalDePreparoEmSegundos() {
        return tempoTotalDePreparoEmSegundos;
    }

    public void setTempoTotalDePreparoEmSegundos(Long tempoTotalDePreparoEmSegundos) {
        this.tempoTotalDePreparoEmSegundos = tempoTotalDePreparoEmSegundos;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public void removeProduto(Produto produto) {
        this.produtos.remove(produto);
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }
}
