package br.imd.Market.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
@Entity
public class PedidoEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "Codigo")
    String codigo;





    // Nome da tabela de junção
    // Chave estrangeira para Pedido
    // Chave estrangeira para Produto
    @NotNull
    @ManyToMany
    @JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<ProdutoEntity> produtos;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    private boolean ativo = true;


}
