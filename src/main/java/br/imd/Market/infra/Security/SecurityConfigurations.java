package br.imd.Market.infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//Classe de configuração do Spring Security
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;


    //Configurando a cadeia de filtro para verificar as roles dos usuários
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{


        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        //ROLES USER
                        .requestMatchers(HttpMethod.GET,"/produtos/getAll").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/produtos/getById/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/pedidos/getAll").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/pedidos/getById/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/clientes/getAll").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/clientes/getById/{id}").hasRole("USER")

                        //ROLES ADMIN
                        .requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/produtos/postProduto").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/produtos/DeleteLogic/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/produtos/DeleteProduto/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/produtos/putProduto/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/produtos/getAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/produtos/getById/{id}").hasRole("ADMIN")
                        //PEDIDOS
                        .requestMatchers(HttpMethod.POST, "/pedidos/postPedido").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pedidos/putPedido/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pedidos/DeleteLogic/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pedidos/DeletePedido/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pedidos/getAll/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pedidos/getById/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pedidos/AdicionarProduto/{Id}/{Id2}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pedidos/RemoverProduto/{Id}/{Id2}").hasRole("ADMIN")

                        //CLIENTE
                        .requestMatchers(HttpMethod.POST, "/clientes/postCliente").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/clientes/putCliente/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/clientes/DeleteLogic/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/clientes/DeleteCliente/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/clientes/getAll/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/clientes/getById/{id}").hasRole("ADMIN")





                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Metodo para codificar a senha em HASH
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
