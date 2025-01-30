package com.leodelmiro.pedido.core.domain;

import java.math.BigDecimal;

public class ItemPedido {
    private Long id;
    private Long idProduto;
    private int quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Long idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public ItemPedido(Long id, Long idProduto, int quantidade) {
        this.id = id;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean temProduto(Long idProduto) {
        return this.getIdProduto().equals(idProduto);
    }

    public BigDecimal valorTotal(Produto produto) {
        return produto.getPreco().multiply(BigDecimal.valueOf(this.quantidade));
    }
}
