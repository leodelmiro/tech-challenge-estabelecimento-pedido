package com.leodelmiro.estabelecimento.adapters.out.repository;

import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    @Query("SELECT p FROM ProdutoEntity p LEFT JOIN FETCH p.imagens WHERE p.categoria = 0")
    List<ProdutoEntity> listarPorLanches();
    @Query("SELECT p FROM ProdutoEntity p LEFT JOIN FETCH p.imagens WHERE p.categoria = 1")
    List<ProdutoEntity> listarPorAcompanhamentos();
    @Query("SELECT p FROM ProdutoEntity p LEFT JOIN FETCH p.imagens WHERE p.categoria = 2")
    List<ProdutoEntity> listarPorBebidas();
    @Query("SELECT p FROM ProdutoEntity p LEFT JOIN FETCH p.imagens WHERE p.categoria = 3")
    List<ProdutoEntity> listarPorSobremesas();
}
