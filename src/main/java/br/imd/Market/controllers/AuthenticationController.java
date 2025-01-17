package br.imd.Market.controllers;

import br.imd.Market.DTO.AuthenticationDTO;
import br.imd.Market.DTO.LoginResponseDTO;
import br.imd.Market.DTO.RegisterDTO;
import br.imd.Market.infra.Security.TokenService;
import br.imd.Market.model.UsersEntity;
import br.imd.Market.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    //Mapping para login do usuário
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        //Fazendo a validação do login e senha do usuário
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsersEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    //Mapping para registro de novos usuários(apenas ADMIN tem permissão para cadastro)
    @PostMapping("/register")
    public ResponseEntity<UsersEntity> register(@RequestBody @Valid RegisterDTO data){
        if (userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsersEntity newUser = new UsersEntity(data.login(), encryptedPassword, data.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
