package com.leodelmiro.pedido.core.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemPedido {
    private Long id;
    private Long produtoId;
    private int quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public ItemPedido(Long id, Long produtoId, int quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean temProduto(Long idProduto) {
        return this.getProdutoId().equals(idProduto);
    }

    public BigDecimal valorTotal(Produto produto) {
        return produto.getPreco().multiply(BigDecimal.valueOf(this.quantidade));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return quantidade == that.quantidade && Objects.equals(id, that.id) && Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produtoId, quantidade);
    }
}
