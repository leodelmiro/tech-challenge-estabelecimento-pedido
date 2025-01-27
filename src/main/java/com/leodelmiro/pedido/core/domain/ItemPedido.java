package com.leodelmiro.pedido.core.domain;

import java.math.BigDecimal;

public class ItemPedido {
    private Long id;
    private Produto produto;
    private int quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido(Long id, Produto produto, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean temProduto(Long idProduto) {
        return this.getProduto().getId().equals(idProduto);
    }

    public BigDecimal valorTotal() {
        return this.produto.getPreco().multiply(BigDecimal.valueOf(this.quantidade));
    }
}
