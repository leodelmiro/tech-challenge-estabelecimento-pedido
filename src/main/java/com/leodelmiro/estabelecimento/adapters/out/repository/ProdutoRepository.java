package com.leodelmiro.estabelecimento.adapters.out.repository;

import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
