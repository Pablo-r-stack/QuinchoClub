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
@RequestMapping("/usuario")
public class UsuarioControlador {
    @GetMapping("/registrar")
    public String registroUsuario(){
        return "register.html";
    }
    @PostMapping("/registrar")
    public String registrarUsuario(){
        return null;
    }
    @GetMapping("/login")
    public String loginUsuario(){
        return "login.html";
    }
    @GetMapping("/lista")
    public String listarUsuarios(){
        return "listaUsuarios.html";
    }
    @PutMapping("/modificar")
    public String modificarUsuario(@PathVariable String id){
        return null;
    }
    @DeleteMapping("/eliminar")
    public String eliminarUsuario(@PathVariable String id){
        return null;
    }
}
