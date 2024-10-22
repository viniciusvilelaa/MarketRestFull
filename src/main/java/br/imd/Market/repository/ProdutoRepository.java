package br.imd.Market.repository;

import br.imd.Market.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    //Sobrescrever o metodo findall para retonar apenas os elemetos ativos
    List<ProdutoEntity> findAllByAtivoTrue();


    Optional<ProdutoEntity> findByIdAndAtivoTrue(Long id);
}
