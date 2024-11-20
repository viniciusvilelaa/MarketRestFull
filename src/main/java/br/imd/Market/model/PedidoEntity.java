package br.imd.Market.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotBlank(message = "Campo codigo do pedido nao informado")
    @Column(name = "Codigo")
    String codigo;





    // Nome da tabela de junção
    // Chave estrangeira para Pedido
    // Chave estrangeira para Produto

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    @NotEmpty(message = "Lista de produtos do pedido nao informado")
    private List<ProdutoEntity> produtos;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "Cliente do pedido nao informado ou nao encontrado")
    private ClienteEntity cliente;

    private boolean ativo = true;


}
