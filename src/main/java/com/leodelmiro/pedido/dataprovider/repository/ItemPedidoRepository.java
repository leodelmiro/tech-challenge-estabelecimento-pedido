package com.leodelmiro.pedido.dataprovider.repository;


import com.leodelmiro.pedido.dataprovider.repository.entity.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {
}
