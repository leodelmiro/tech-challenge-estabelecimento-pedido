package com.leodelmiro.pedido.dataprovider.repository.entity;

import com.leodelmiro.pedido.core.domain.ItemPedido;
import com.leodelmiro.pedido.core.domain.Pedido;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    private Long produtoId;

    private int quantidade;

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(PedidoEntity pedido, Long produtoId, int quantidade) {
        this.pedido = pedido;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public ItemPedidoEntity(ItemPedido itemPedido) {
        this.produtoId = itemPedido.getProdutoId();
        this.quantidade = itemPedido.getQuantidade();
    }

    public ItemPedidoEntity(Long id, PedidoEntity pedido, Long produtoId, int quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public ItemPedidoEntity(ItemPedido itemPedido, Pedido pedido) {
        this(itemPedido.getId(), new PedidoEntity(pedido), itemPedido.getProdutoId(), itemPedido.getQuantidade());
    }

    public static ItemPedidoEntity doDominio(ItemPedido itemPedido, Long produtoEntity) {
        return new ItemPedidoEntity(null, produtoEntity, itemPedido.getQuantidade());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
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

    public ItemPedido toItemPedido() {
        return new ItemPedido(this.id, this.produtoId, this.quantidade);
    }
}
