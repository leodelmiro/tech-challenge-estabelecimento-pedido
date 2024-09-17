package com.leodelmiro.estabelecimento.dataprovider.repository;

import com.leodelmiro.estabelecimento.dataprovider.repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findClienteByCpf(String cpf);
}
