package com.leodelmiro.estabelecimento.dataprovider.repository.entity;

import com.leodelmiro.estabelecimento.core.domain.Categoria;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;

    private BigDecimal preco;

    private String descricao;

    private Long tempoDePreparoEmSegundos;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImagemEntity> imagens;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Long id,
                         String nome,
                         Categoria categoria,
                         BigDecimal preco,
                         String descricao,
                         Long tempoDePreparoEmSegundos,
                         LocalDateTime criadoEm,
                         Set<ImagemEntity> imagens) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
        this.tempoDePreparoEmSegundos = tempoDePreparoEmSegundos;
        this.criadoEm = criadoEm;
        this.imagens = imagens;
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
        this.preco = preco;
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

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Set<ImagemEntity> getImagens() {
        return imagens;
    }

    public void setImagens(Set<ImagemEntity> imagens) {
        this.imagens = imagens;
    }

    public Long getTempoDePreparoEmSegundos() {
        return tempoDePreparoEmSegundos;
    }

    public void setTempoDePreparoEmSegundos(Long tempoDePreparoEmSegundos) {
        this.tempoDePreparoEmSegundos = tempoDePreparoEmSegundos;
    }
}
