package br.imd.Market.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    String nomeProduto;
    String marca;
    String dataFabricacao;
    String dataValidade;
    String genero;
    String lote;

}
