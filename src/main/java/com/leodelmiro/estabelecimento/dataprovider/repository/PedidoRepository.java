package com.leodelmiro.estabelecimento.dataprovider.repository;


import com.leodelmiro.estabelecimento.dataprovider.repository.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    @Query("SELECT p FROM PedidoEntity p WHERE p.status NOT IN (PENDENTE_FECHAMENTO, AGUARDANDO_PAGAMENTO, FINALIZADO) ORDER BY p.status DESC, p.pagoEm")
    List<PedidoEntity> findPedidosPagosEPendentesDeFinalizacao();
}
