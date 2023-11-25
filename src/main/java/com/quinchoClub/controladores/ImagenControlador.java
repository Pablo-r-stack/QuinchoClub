/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.quinchoClub.controladores;

import com.quinchoClub.entidades.Imagen;
import com.quinchoClub.servicios.ImagenServicio;
import com.quinchoClub.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pablo
 */
@Controller
@RequestMapping("/imagen")
public class ImagenControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ImagenServicio imagenServicio;
    @GetMapping("/vista/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id){
        //Usuario usuario = usuarioServicio.getOne(id);
        Imagen archivo =imagenServicio.getOne(id);
        byte[] imagen = archivo.getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
}
