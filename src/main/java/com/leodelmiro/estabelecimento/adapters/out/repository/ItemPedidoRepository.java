package com.leodelmiro.estabelecimento.adapters.out.repository;


import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {
}
