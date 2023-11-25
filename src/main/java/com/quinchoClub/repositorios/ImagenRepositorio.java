/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quinchoClub.repositorios;

import com.quinchoClub.entidades.Imagen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pablo
 */
@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {
    @Query("SELECT i FROM Imagen i  WHERE i.nombre =:nombre")
    List<Imagen>encontrarPorNombre(@Param("nombre") String nombre);
}
