package com.leodelmiro.estabelecimento.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.leodelmiro.estabelecimento.core.domain.StatusPedido.PENDENTE_FECHAMENTO;

public class Pedido {
    private Long id;
    private Cliente cliente;
    private BigDecimal precoTotal;
    private StatusPedido status;
    private Long tempoTotalDePreparoEmSegundos;
    private LocalDateTime pagoEm;
    private LocalDateTime criadoEm;

    private final List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Cliente cliente,
                  StatusPedido status,
                  BigDecimal precoTotal,
                  Long tempoTotalDePreparoEmSegundos) {
        if (cliente == null) throw new IllegalArgumentException("Cliente não pode ser null");
        if (status == null) throw new IllegalArgumentException("Status não pode ser null");
        if (precoTotal == null || precoTotal.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Preço deve ser igual ou maior que 0");
        if (tempoTotalDePreparoEmSegundos == null || tempoTotalDePreparoEmSegundos < 0)
            throw new IllegalArgumentException("Tempo de preparo deve ser igual ou maior que 0");

        this.cliente = cliente;
        this.status = status;
        this.precoTotal = precoTotal;
        this.tempoTotalDePreparoEmSegundos = tempoTotalDePreparoEmSegundos;
    }

    public Pedido(Long id,
                  Cliente cliente,
                  BigDecimal precoTotal,
                  StatusPedido status,
                  Long tempoTotalDePreparoEmSegundos,
                  LocalDateTime criadoEm) {
        if (cliente == null) throw new IllegalArgumentException("Cliente não pode ser null");
        if (precoTotal == null || precoTotal.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Preço deve ser igual ou maior que 0");
        if (status == null) throw new IllegalArgumentException("Status não pode ser null");
        if (tempoTotalDePreparoEmSegundos == null || tempoTotalDePreparoEmSegundos < 0)
            throw new IllegalArgumentException("Tempo de preparo deve ser igual ou maior que 0");

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

    public void removeItem(ItemPedido item) {
        if (estaPedidoSemItens()) throw new IllegalStateException("Impossível remover itens de pedido vazio");

        this.itens.remove(item);
    }

    private boolean estaPedidoSemItens() {
        return this.getItens().isEmpty();
    }

    public void addItem(ItemPedido item) {
        itens.add(item);
    }

    public void addItens(List<ItemPedido> itens) {
        if (estaPedidoFechado())
            throw new IllegalStateException("Impossível adicionar produtos,pedido já foi fechado");

        this.itens.addAll(itens);
    }

    private boolean estaPedidoFechado() {
        return this.getStatus() != PENDENTE_FECHAMENTO;
    }

    public List<ItemPedido> getItens() {
        return this.itens;
    }

    public ItemPedido getItemPedido(Long idProduto) {
        return itens.stream()
                .filter(item -> item.getProduto().getId().equals(idProduto))
                .findFirst()
                .orElse(null);
    }

    public void atualizaQuantidadeItemPedido(Long idItem, int novaQuantidade) {
        var itemPedido = this.getItemPedido(idItem);
        if (novaQuantidade >= itemPedido.getQuantidade())
            throw new IllegalArgumentException("Quantidade não pode ser maior ou igual a do item");
        itemPedido.setQuantidade(novaQuantidade);
    }

    public LocalDateTime getPagoEm() {
        return pagoEm;
    }

    public void setPagoEm(LocalDateTime pagoEm) {
        this.pagoEm = pagoEm;
    }

    public ItemPedido getItemPedidoPorProduto(Long idProduto) {
        return this.itens.stream().filter(itemPedido -> itemPedido.temProduto(idProduto)).findFirst().orElse(null);
    }

    public boolean temProduto(Long idProduto) {
        return getItemPedidoPorProduto(idProduto) != null;
    }

    public void avancarStatus() {
        if (this.getStatus() == StatusPedido.AGUARDANDO_PAGAMENTO && !this.estaPago())
            throw new IllegalStateException("Produto precisa ser pago para avançar");
        this.getStatus().next();
    }

    private boolean estaPago() {
        return pagoEm != null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(cliente, pedido.cliente) && Objects.equals(precoTotal, pedido.precoTotal) && status == pedido.status && Objects.equals(tempoTotalDePreparoEmSegundos, pedido.tempoTotalDePreparoEmSegundos) && Objects.equals(pagoEm, pedido.pagoEm) && Objects.equals(criadoEm, pedido.criadoEm) && Objects.equals(itens, pedido.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, precoTotal, status, tempoTotalDePreparoEmSegundos, pagoEm, criadoEm, itens);
    }
}
