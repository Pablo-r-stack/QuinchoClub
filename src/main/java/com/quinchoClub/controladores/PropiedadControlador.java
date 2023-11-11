/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {
    @GetMapping("/lista")
    public String listarPropiedades(){
        return "listaPropiedades.html";
    }
    @PostMapping("/cargar")
    public String cargarPropiedad(){
        return null;
    }
    @PutMapping("/modificar")
    public String modificarPropiedad(@PathVariable String id){
        return null;
    }
    @DeleteMapping("/eliminar")
    public String eliminarPropiedad(@PathVariable String id){
        return null;
    }
}
