package com.leodelmiro.estabelecimento.application.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Produto {
    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private String descricao;
    private final LocalDateTime criadoEm = LocalDateTime.now();
    private final Set<Imagem> imagens = new HashSet<>();

    public Produto() {
    }

    public Produto(Long id,
                   String nome,
                   Categoria categoria,
                   BigDecimal preco,
                   String descricao) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco.setScale(2);
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco.setScale(2);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }

    public void addImagem(Imagem imagem) {
        this.imagens.add(imagem);
    }

    public void addImagens(Collection<Imagem> imagens) {
        this.imagens.addAll(imagens);
    }

    public void removeImagem(Long id) {
        this.imagens.removeIf(imagem -> imagem.getId().equals(id));
    }
}
