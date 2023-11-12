/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quinchoClub.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pablo Velasco
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
     @GetMapping("/")
     public String vistaIndex(){
         return "index.html";
     } 
}
