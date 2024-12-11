package br.imd.Market.repository;

import br.imd.Market.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UsersEntity, String> {
    UserDetails findByLogin(String login);
}
