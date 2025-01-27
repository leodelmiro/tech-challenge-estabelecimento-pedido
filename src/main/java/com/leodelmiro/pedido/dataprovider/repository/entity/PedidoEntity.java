package com.leodelmiro.pedido.dataprovider.repository.entity;

import com.leodelmiro.pedido.core.domain.StatusPedido;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clienteId;

    private BigDecimal precoTotal;

    @Enumerated(EnumType.ORDINAL)
    private StatusPedido status;

    private Long tempoTotalDePreparoEmSegundos;

    private String ordemPagamentoId;

    private LocalDateTime pagoEm;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ItemPedidoEntity> itens = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
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

    public LocalDateTime getPagoEm() {
        return pagoEm;
    }

    public void setPagoEm(LocalDateTime pagoEm) {
        this.pagoEm = pagoEm;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public void removeItem(ItemPedidoEntity item) {
        this.itens.remove(item);
    }

    public void addItem(ItemPedidoEntity item) {
        this.itens.add(item);
    }

    public void addItens(List<ItemPedidoEntity> itens) {
        this.itens.addAll(itens);
    }

    public List<ItemPedidoEntity> getItens() {
        return this.itens;
    }

    public String getOrdemPagamentoId() {
        return ordemPagamentoId;
    }

    public void setOrdemPagamentoId(String ordemPagamentoId) {
        this.ordemPagamentoId = ordemPagamentoId;
    }
}
