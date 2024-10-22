package br.imd.Market.model;

import jakarta.persistence.*;
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

    @Column(name = "Nome")
    String nomeProduto;

    @Column(name = "Marca")
    String marca;

    @Column(name = "Data de Fabricação")
    String dataFabricacao;


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



}
