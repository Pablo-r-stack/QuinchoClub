/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quinchoClub.repositorios;

import com.quinchoClub.entidades.Resena;
import com.quinchoClub.entidades.ResenaPropiedad;
import com.quinchoClub.entidades.ResenaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gabi
 */
@Repository
public interface ResenaRepositorio extends JpaRepository<Resena, String>{
    @Query("SELECT u FROM ResenaPropiedad u WHERE u.propiedad = :propiedad")
    public ResenaPropiedad buscarResenaAPropiedad(@Param("propiedad") String propiedad);
    @Query("SELECT u FROM ResenaUsuario u WHERE u.usuario = :usuario")
    public ResenaUsuario buscarResenaAUsuario(@Param("usuario") String usuario);
}
