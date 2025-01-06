package br.imd.Market.model;

import lombok.Getter;


//Enum das regras
@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user");


    private String role;

    UserRole(String role){
        this.role= role;
    }
}
