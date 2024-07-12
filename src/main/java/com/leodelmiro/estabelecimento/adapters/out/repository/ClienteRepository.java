package com.leodelmiro.estabelecimento.adapters.out.repository;

import com.leodelmiro.estabelecimento.adapters.out.repository.entity.ClienteEntity;
import com.leodelmiro.estabelecimento.application.core.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findClienteByCpf(String cpf);
}
