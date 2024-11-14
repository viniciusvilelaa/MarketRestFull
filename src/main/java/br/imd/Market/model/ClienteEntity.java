package br.imd.Market.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "clientes")
public class ClienteEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NotBlank(message = "Nome não informado")
    @Column(name = "Nome")
    String nome;

    @CPF(message = "Campo Invalido")
    @NotBlank(message = "Cpf não informado")
    @Column(name = "Cpf")
    String cpf;


    @Column(name = "Genero")
    @Enumerated(EnumType.STRING)
    Genero genero;

    public enum Genero {
        MASCULINO, FEMININO
    }

    @NotBlank(message = "Data de nascimento não informada")
    @Column(name = "Data de Nascimento")
    String dataNascimento;

    private boolean ativo = true;


}
