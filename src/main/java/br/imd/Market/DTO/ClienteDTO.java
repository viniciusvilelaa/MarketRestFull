package br.imd.Market.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    String nome;
    String cpf;
    String genero;
    String dataNascimento;
}
