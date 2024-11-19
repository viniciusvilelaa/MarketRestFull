package br.imd.Market.DTO;

import br.imd.Market.model.ClienteEntity;
import br.imd.Market.model.ProdutoEntity;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    String codigo;


    List<Long> produtosId;

    Long clienteId;

}
