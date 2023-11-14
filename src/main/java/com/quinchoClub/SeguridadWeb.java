/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quinchoClub;

import com.quinchoClub.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author lauta
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb  extends WebSecurityConfigurerAdapter{
    @Autowired
    private UsuarioServicio us;
    @Autowired
  private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(us) //indicamos que el servicio es quien se encarga de validar los usuarios
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
                .antMatchers("/usuario/registrar").permitAll()
                .antMatchers("/usuario/registro").permitAll()
                .and().formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/logincheck")
                .defaultSuccessUrl("/")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and().csrf()
                .disable();

    }
    
}