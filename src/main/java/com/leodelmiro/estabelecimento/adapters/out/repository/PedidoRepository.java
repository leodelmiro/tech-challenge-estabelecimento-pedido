package com.leodelmiro.estabelecimento.adapters.out.repository;

import com.leodelmiro.estabelecimento.adapters.out.repository.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
