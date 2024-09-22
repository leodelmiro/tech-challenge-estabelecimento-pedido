package com.leodelmiro.estabelecimento.core.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Produto {
    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private String descricao;
    private Long tempoDePreparoEmSegundos;
    private LocalDateTime criadoEm;
    private final Set<Imagem> imagens = new HashSet<>();

    public Produto() {
        criadoEm = LocalDateTime.now();
    }

    public Produto(Long id,
                   String nome,
                   Categoria categoria,
                   BigDecimal preco,
                   String descricao,
                   Long tempoDePreparoEmSegundos,
                   LocalDateTime criadoEm
    ) {
        if (nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio");
        if (categoria == null) throw new IllegalArgumentException("Categoria não pode ser null");
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Preço deve ser igual ou maior que 0");
        if (descricao.isBlank()) throw new IllegalArgumentException("Descrição não pode ser vazio");
        if (tempoDePreparoEmSegundos == null || tempoDePreparoEmSegundos < 0)
            throw new IllegalArgumentException("Tempo de preparo deve ser igual ou maior que 0");

        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco.setScale(2, RoundingMode.DOWN);
        this.descricao = descricao;
        this.tempoDePreparoEmSegundos = tempoDePreparoEmSegundos;
        this.criadoEm = (criadoEm == null) ? LocalDateTime.now() : criadoEm;
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
        this.preco = preco.setScale(2, RoundingMode.DOWN);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = (criadoEm == null) ? LocalDateTime.now() : criadoEm;
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

    public Long getTempoDePreparoEmSegundos() {
        return tempoDePreparoEmSegundos;
    }

    public void setTempoDePreparoEmSegundos(Long tempoDePreparoEmSegundos) {
        this.tempoDePreparoEmSegundos = tempoDePreparoEmSegundos;
    }
}
