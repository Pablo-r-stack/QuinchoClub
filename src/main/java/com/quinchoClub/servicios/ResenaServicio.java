/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.servicios;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.entidades.ResenaPropiedad;
import com.quinchoClub.entidades.ResenaUsuario;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.repositorios.ResenaRepositorio;
import java.time.LocalDate;
import java.util.List;
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
    @Autowired
    private PropiedadServicio ps;
    @Autowired
    private UsuarioServicio us;
    //CRUD DE RESENAS.
    public void crearResenaPropiedad(Usuario usuario, String comentario, Integer calificacion, Propiedad propiedad) {
        ResenaPropiedad resena = new ResenaPropiedad();
        resena.setUsuario(usuario);
        resena.setComentario(comentario);
        resena.setCalificacion(calificacion);
        resena.setFechaComentario(LocalDate.now());
        rr.save(resena);
        List<ResenaPropiedad> resenas = propiedad.getResenas();
        resenas.add(resena);
        propiedad.setResenas(resenas);
        ps.actualizarPropiedad(propiedad);
    }
    public void crearResenaUsuario(Usuario usuario, String comentario, Integer calificacion, Usuario cliente){
        ResenaUsuario resena = new ResenaUsuario();
        resena.setUsuario(usuario);
        resena.setComentario(comentario);
        resena.setCalificacion(calificacion);
        resena.setFechaComentario(LocalDate.now());
        rr.save(resena);
        List<ResenaUsuario> resenas = cliente.getResenaDeUsario();
        resenas.add(resena);
        cliente.setResenaDeUsario(resenas);
        us.guardarUsuarioCompleto(usuario);
    }
}
