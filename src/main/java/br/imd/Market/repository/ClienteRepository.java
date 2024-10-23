package br.imd.Market.repository;

import br.imd.Market.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {

    List<ClienteEntity> findAllByAtivoTrue();

    Optional<ClienteEntity> findByIdAndAtivoTrue(Long id);
}
