package br.imd.Market.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NotBlank(message = "Nome do produto não informado")
    @Column(name = "Nome")
    String nomeProduto;

    @NotBlank(message = "Marca do produto não informado")
    @Column(name = "Marca")
    String marca;

    @NotBlank(message = "Data de fab do produto não informada")
    @Column(name = "Data de Fabricação")
    String dataFabricacao;

    @NotBlank(message = "Data de validade do produto não informada")
    @Column(name = "Data de Validade", nullable = true)
    String dataValidade;

    @Column(name = "Genero")
    @Enumerated(EnumType.STRING)
    GeneroEnum genero;

    public enum GeneroEnum {
        cosmético,
        alimentício,
        higienePessoal,
        limpeza
    }

    @Column(name = "Lote")
    String lote;

    private boolean ativo = true;

}
