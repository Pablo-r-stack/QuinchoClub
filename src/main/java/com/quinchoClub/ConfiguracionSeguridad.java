/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub;

import com.quinchoClub.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Lauta
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioServicio us;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(us)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/css/*", "/js/*", "/img/*", "/", "/usuario/login", "/usuario/registrar", "/imagen/vista/**", "/propiedad/publicacion/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                    .loginPage("/usuario/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/logincheck")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/usuario/login")
                .and()
                .csrf().disable();
               
    }
}
