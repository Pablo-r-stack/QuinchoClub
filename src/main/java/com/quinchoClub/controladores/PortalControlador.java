/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.controladores;

import com.quinchoClub.entidades.Propiedad;
import com.quinchoClub.entidades.Usuario;
import com.quinchoClub.servicios.PropiedadServicio;
import com.quinchoClub.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pablo Velasco
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
    @Autowired
    private UsuarioServicio us;
    @Autowired
    private PropiedadServicio ps;
     @GetMapping("/")
     public String vistaIndex(ModelMap modelo, HttpSession session){
         Usuario usuario = (Usuario) session.getAttribute("usuariosession");
         if(usuario != null){
             modelo.put("usuario", us.getOne(usuario.getId()));
         }
         List<Propiedad> propiedades = ps.obtenerTodasLasPropiedades();
         modelo.addAttribute("propiedades",propiedades);
         return "index.html";
     } 
}
