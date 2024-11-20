package br.imd.Market.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "clientes")
public class ClienteEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NotBlank(message = "Nome do cliente não informado")
    @Column(name = "Nome")
    String nome;

    @CPF(message = "CPF informado nao é valido")
    @NotBlank(message = "CPF do cliente não informado")
    @Column(name = "Cpf")
    String cpf;


    @Column(name = "Genero")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Genero do cliente nao informado")
    Genero genero;

    public enum Genero {
        MASCULINO, FEMININO
    }

    @NotBlank(message = "Data de nascimento não informada")
    @Column(name = "Data de Nascimento")
    String dataNascimento;

    private boolean ativo = true;

    /*@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<PedidoEntity> pedidos;*/

}
