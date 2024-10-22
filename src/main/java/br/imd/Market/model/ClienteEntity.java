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
@Table(name = "clientes")
public class ClienteEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "Nome")
    String nome;

    @Column(name = "Cpf")
    String cpf;

    @Column(name = "Genero")
    @Enumerated(EnumType.STRING)
    Genero genero;

    public enum Genero {
        MASCULINO, FEMININO
    }

    @Column(name = "Data de Nascimento")
    String dataNascimento;




}
