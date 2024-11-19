package br.imd.Market.repository;

import br.imd.Market.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findAllByAtivoTrue();

    Optional<PedidoEntity> findByIdAndAtivoTrue(Long id);
}
