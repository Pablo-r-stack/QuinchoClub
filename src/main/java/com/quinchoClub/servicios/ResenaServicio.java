/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.servicios;

import com.quinchoClub.entidades.ResenaPropiedad;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.repositorios.ResenaRepositorio;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabi
 */
@Service
public class ResenaServicio {

    @Autowired
    private ResenaRepositorio rr;

    //CRUD DE RESENAS.
    public void crearResenaPropiedad(String id, Usuario usuario, String comentario, Integer calificacion, LocalDate fechaComentario) {
        ResenaPropiedad resena = new ResenaPropiedad();
        resena.setUsuario(usuario);
        resena.setComentario(comentario);
        resena.setCalificacion(calificacion);
        resena.setFechaComentario(fechaComentario);
    }
}
