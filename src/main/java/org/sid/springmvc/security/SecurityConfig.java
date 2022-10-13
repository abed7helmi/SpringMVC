package org.sid.springmvc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //methodes pour identifier les users
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER"); // strategie de stocker des user en mémoire
        auth.inMemoryAuthentication().withUser("user2").password("{noop}1234").roles("USER"); // strategie de stocker des user en mémoire
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("USER","ADMIN"); // strategie de stocker des user en mémoire
//        noop pour empecher le hashage de mdp
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(); // auth se sefait par un form par defaut
//        http.formLogin().loginPage("/login");
        //http.httpBasic(); // authentification basic de http ( comme confirmation de suppression) pas un formulaire html
        //http.authorizeRequests().anyRequest().authenticated(); // tt les requettes http besoin d'authentification
        http.authorizeRequests().antMatchers("/save**/**","/delete**/**").hasRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
    }
}
