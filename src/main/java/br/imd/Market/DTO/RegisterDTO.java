package br.imd.Market.DTO;


import br.imd.Market.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
